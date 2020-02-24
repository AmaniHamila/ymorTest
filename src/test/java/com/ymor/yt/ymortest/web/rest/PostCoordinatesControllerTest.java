package com.ymor.yt.ymortest.web.rest;


import com.ymor.yt.ymortest.service.PostcodeCoordinatesService;
import com.ymor.yt.ymortest.service.dto.CoordinatesUpdateDTO;
import com.ymor.yt.ymortest.service.dto.GeographicDistanceDTO;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;



@ExtendWith(MockitoExtension.class)
class PostCoordinatesControllerTest {

    @Mock
    private PostcodeCoordinatesService postcodeCoordinatesService;

    final String baseUrl = "http://localhost:8086/services/postcode-coordinates";
    private final Logger log = LoggerFactory.getLogger(PostCoordinatesControllerTest.class);


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getGeographicDistance() throws URISyntaxException
    {
        String postcode1 = "1111aa";
        String postcode2 = "1111bb";
        String url = baseUrl + "/get-geographic-distance/" + postcode1 + "/" + postcode2;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<GeographicDistanceDTO> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<GeographicDistanceDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                GeographicDistanceDTO.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.debug("Response received : {}", responseEntity.getBody());
        } else {
            log.error("Error occurred : {}", responseEntity.getStatusCode());
        }
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void updatePostcodeCoordinates() throws URISyntaxException {
        String postcode = "1111aa";
        double lat = 53.478612;
        double lon = 6.250578 ;
        String url = baseUrl + "/update-postcode-coordinates/" + postcode + "/" + lat + "/" + lon;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<CoordinatesUpdateDTO> requestEntity = new HttpEntity<>(requestHeaders);
        ResponseEntity<CoordinatesUpdateDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                CoordinatesUpdateDTO.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.debug("Response received : {}", responseEntity.getBody());
        } else {
            log.error("Error occurred : {}", responseEntity.getStatusCode());
        }
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
