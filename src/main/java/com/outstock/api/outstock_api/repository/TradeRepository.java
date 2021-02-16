package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
