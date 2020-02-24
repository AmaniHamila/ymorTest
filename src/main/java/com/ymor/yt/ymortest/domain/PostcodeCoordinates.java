package com.ymor.yt.ymortest.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "postcode_coordinates")
public class PostcodeCoordinates implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String postcode;

    @Column
    private double latitude;

    @Column
    private double longitude;


    public PostcodeCoordinates() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


}
