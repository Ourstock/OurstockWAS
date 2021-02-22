package com.ourstock.api.ourstock_api.repository;

import com.ourstock.api.ourstock_api.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM channels as ch WHERE ch.status NOT IN (:status0, :status8) AND (name_kor LIKE :nameKor OR name_eng LIKE :nameEng)")
    List<Channel> findAllBySearchChannelName(@Param("status0") int status1, @Param("status8") int status8, @Param("nameKor") String channelNameKor, @Param("nameEng") String channelNameEng);

}
