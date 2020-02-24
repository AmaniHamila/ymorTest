package com.ymor.yt.ymortest.repository;

import com.ymor.yt.ymortest.domain.PostcodeCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PostcodeCoordinatesRepository extends JpaRepository<PostcodeCoordinates,Long> {

    Optional<PostcodeCoordinates> findByPostcode(String postcode);

    @Transactional
    @Modifying
    @Query("UPDATE PostcodeCoordinates pcc SET pcc.latitude = :latitude, pcc.longitude = :longitude where pcc.postcode = :postcode")
    int updatePostcodeCoordinates(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("postcode") String postcode);

}
