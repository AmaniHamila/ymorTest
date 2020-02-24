package com.ymor.yt.ymortest.service.dto;

import com.ymor.yt.ymortest.domain.PostcodeCoordinates;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GeographicDistanceDTO implements Serializable {

    private List<PostcodeCoordinates> coordinates;
    private BigDecimal distance;
    private final String unit ="km";


    public GeographicDistanceDTO(){

    }

    public GeographicDistanceDTO(List<PostcodeCoordinates> coordinates, BigDecimal distance) {
        this.coordinates = coordinates;
        this.distance = distance;
    }

    public List<PostcodeCoordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<PostcodeCoordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return unit;
    }
}
