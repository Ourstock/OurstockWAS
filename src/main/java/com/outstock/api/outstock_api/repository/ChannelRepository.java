package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, String> {
}
