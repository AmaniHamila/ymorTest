package com.ymor.yt.ymortest.web.rest;

import com.ymor.yt.ymortest.service.PostcodeCoordinatesService;
import com.ymor.yt.ymortest.service.dto.CoordinatesUpdateDTO;
import com.ymor.yt.ymortest.service.dto.GeographicDistanceDTO;
import com.ymor.yt.ymortest.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@RestController
@RequestMapping("services/postcode-coordinates")
public class PostCoordinatesController {

    private final Logger log = LoggerFactory.getLogger(PostCoordinatesController.class);
    private final PostcodeCoordinatesService postcodeCoordinatesService;
    private final String REGEX_DUTCH_POSTCODE = "^[1-9][0-9]{3} ?(?!sa|sd|ss|SA|SD|SS)[A-Za-z]{2}$";


    public PostCoordinatesController(PostcodeCoordinatesService postcodeCoordinatesService) {
        this.postcodeCoordinatesService = postcodeCoordinatesService;
    }

    @PutMapping("/get-geographic-distance/{postcode1}/{postcode2}")
    public ResponseEntity<GeographicDistanceDTO> getGeographicDistance(@PathVariable String postcode1, @PathVariable String postcode2 ) {

        if(Pattern.matches(this.REGEX_DUTCH_POSTCODE,postcode1) && Pattern.matches(this.REGEX_DUTCH_POSTCODE,postcode2)){
            return new ResponseEntity<GeographicDistanceDTO>(this.postcodeCoordinatesService.getGeographicDistance(postcode1.toUpperCase(),postcode2.toUpperCase()), HttpStatus.OK);
        }else {
            throw new BadRequestAlertException("This is not a dutch postcode ", "PostcodeCoordinates", "postcode_not_dutch");
        }
    }

    @PutMapping("/update-postcode-coordinates/{postcode}/{latitude}/{longitude}")
    public ResponseEntity<CoordinatesUpdateDTO> updatePostcodeCoordinates(@PathVariable String postcode, @PathVariable Double latitude, @PathVariable Double longitude) {

        if(Pattern.matches(this.REGEX_DUTCH_POSTCODE,postcode)){
            return new ResponseEntity<CoordinatesUpdateDTO>(this.postcodeCoordinatesService.updatePostcode(postcode.toUpperCase(),latitude,longitude), HttpStatus.OK);
        }else {
            throw new BadRequestAlertException("This is not a dutch postcode ", "PostcodeCoordinates", "postcode_not_dutch");
        }
    }

}
