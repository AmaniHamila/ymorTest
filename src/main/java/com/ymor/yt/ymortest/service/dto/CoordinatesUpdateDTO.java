package com.ymor.yt.ymortest.service.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CoordinatesUpdateDTO implements Serializable {
    private String message;
    private String postcode;
    private Map<String,Double> newCoordinates = new HashMap<>();

    public CoordinatesUpdateDTO(){

    }

    public CoordinatesUpdateDTO(String message, String postcode, Map<String, Double> newCoordinates) {
        this.message = message;
        this.postcode = postcode;
        this.newCoordinates = newCoordinates;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Map<String, Double> getNewCoordinates() {
        return newCoordinates;
    }

    public void setNewCoordinates(Map<String, Double> newCoordinates) {
        this.newCoordinates = newCoordinates;
    }
}


