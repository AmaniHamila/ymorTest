package com.ymor.yt.ymortest.service;

import com.ymor.yt.ymortest.service.dto.CoordinatesUpdateDTO;
import com.ymor.yt.ymortest.service.dto.GeographicDistanceDTO;
import com.ymor.yt.ymortest.service.util.DistanceUtils;

import com.ymor.yt.ymortest.web.rest.errors.BadRequestAlertException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.ymor.yt.ymortest.domain.PostcodeCoordinates;
import com.ymor.yt.ymortest.repository.PostcodeCoordinatesRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
public class PostcodeCoordinatesServiceTest {

    @InjectMocks
    private PostcodeCoordinatesService postcodeCoordinatesService;

    @Mock(lenient = true)
    private PostcodeCoordinatesRepository postcodeCoordinatesRepository;


    private double Precision = 0.000001;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGeographicDistance() throws Exception{
        PostcodeCoordinates p1 = new PostcodeCoordinates();
        PostcodeCoordinates p2 = new PostcodeCoordinates();
        DistanceUtils d = new DistanceUtils();
        List<PostcodeCoordinates> plist = new ArrayList<>();

        double expectedDistance = 304.001021; // Km
        p1.setId(1L);
        p1.setLatitude(53.478612);
        p1.setLongitude(6.250578);
        p1.setPostcode("1111aa");

        p2.setId(2L);
        p2.setLatitude(50.752342);
        p2.setLongitude(5.916981);
        p2.setPostcode("2222bb");


        Mockito.when(postcodeCoordinatesRepository.findByPostcode(any(String.class))).thenReturn(java.util.Optional.of(p1));
        Mockito.when(postcodeCoordinatesRepository.findByPostcode(any(String.class))).thenReturn(java.util.Optional.of(p2));

        plist.add(p1);
        plist.add(p2);

        Exception exception = assertThrows(
                BadRequestAlertException.class,
                () -> postcodeCoordinatesService.getPostCoordinates(anyString()));
        assertTrue(exception.getMessage().contains("This postcode doesn't exist"));

        double resultDistance = d.calculateDistance(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
        assertEquals(expectedDistance, resultDistance, Precision);
    }


    @Test
    public void testUpdatePostcode() throws Exception {
        Mockito.when(postcodeCoordinatesRepository.updatePostcodeCoordinates(any(Double.class),any(Double.class),any(String.class))).thenReturn(1);
        CoordinatesUpdateDTO result = postcodeCoordinatesService.updatePostcode(any(String.class),any(Double.class),any(Double.class));
        assertNotNull(result, () -> "The result should not be null");

    }

}
