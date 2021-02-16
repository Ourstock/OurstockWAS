package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, String> {

//    @Query("select ch from channels ch where ch.hidden_subscriber_count = 1 and ch.subscriber_count >= ?1")
    List<Channel> findAllBySubscriberCount(long scribeCount);

}
