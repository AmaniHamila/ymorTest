package com.ymor.yt.ymortest.service;

import com.ymor.yt.ymortest.domain.PostcodeCoordinates;
import com.ymor.yt.ymortest.repository.PostcodeCoordinatesRepository;
import com.ymor.yt.ymortest.service.dto.CoordinatesUpdateDTO;
import com.ymor.yt.ymortest.service.dto.GeographicDistanceDTO;
import com.ymor.yt.ymortest.service.util.DistanceUtils;
import com.ymor.yt.ymortest.web.rest.errors.BadRequestAlertException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional
public class PostcodeCoordinatesService {

    public final PostcodeCoordinatesRepository postcodeCoordinatesRepository;

    public PostcodeCoordinatesService(
            PostcodeCoordinatesRepository postcodeCoordinatesRepository
    ) {
        this.postcodeCoordinatesRepository = postcodeCoordinatesRepository;
    }


    public GeographicDistanceDTO getGeographicDistance(String postcode1, String postcode2) {
        PostcodeCoordinates p1 = this.getPostCoordinates(postcode1);
        PostcodeCoordinates p2 = this.getPostCoordinates(postcode2);
        GeographicDistanceDTO geographicDistanceDTO = new GeographicDistanceDTO();
        List<PostcodeCoordinates> pcList = new ArrayList<>();
        DistanceUtils d = new DistanceUtils();
        pcList.add(p1);
        pcList.add(p2);
        geographicDistanceDTO.setCoordinates(pcList);
        geographicDistanceDTO.setDistance(BigDecimal.valueOf(d.calculateDistance(p1.getLatitude(),p1.getLongitude(),p2.getLatitude(),p2.getLongitude())).setScale(2, RoundingMode.HALF_UP));
       return geographicDistanceDTO;
    }


    public PostcodeCoordinates getPostCoordinates(String postcode){
        PostcodeCoordinates pc = this.postcodeCoordinatesRepository.findByPostcode(postcode).orElseThrow(
                () -> new BadRequestAlertException("This postcode doesn't exist ", "PostcodeCoordinates", "unexisting_postcode"));
        return pc;
    }

    public CoordinatesUpdateDTO updatePostcode(String postcode,double latitude, double longitude) {
        CoordinatesUpdateDTO coordinatesUpdateDTO = new CoordinatesUpdateDTO();
        if (this.postcodeCoordinatesRepository.updatePostcodeCoordinates(latitude,longitude,postcode) > 0){
            coordinatesUpdateDTO.setMessage("coordinates are successfully updated");
        } else {
            coordinatesUpdateDTO.setMessage("an error occured");
        }
        coordinatesUpdateDTO.setPostcode(postcode);
        Map<String,Double> newCoordinates = new HashMap<>();
        newCoordinates.put("latitude",latitude);
        newCoordinates.put("longitude",longitude);
        coordinatesUpdateDTO.setNewCoordinates(newCoordinates);
        return coordinatesUpdateDTO;
    }
}
