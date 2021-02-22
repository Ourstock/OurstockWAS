package com.ourstock.api.ourstock_api.repository;

import com.ourstock.api.ourstock_api.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

//    Trade에 실거래가 발생하게되면
//    그 거래를 실시간 데이터에 반영해야한다.
//    그렇다면 그 데이터들의 정보는 어떠한 정보인가.
//    거래의 가격, 개수, 시간, channelId,
}
