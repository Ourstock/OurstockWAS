package com.ourstock.api.ourstock_api.service;


import com.ourstock.api.ourstock_api.repository.TradeRepository;
import com.ourstock.api.ourstock_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;

    private TradeService(TradeRepository tradeRepository, UserRepository userRepository) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
    }



}


// 먼저 order 로 주문을 한다 매도 or 매수
// 매도신청
// 매수
// 매수신청 을 하게되면 원하는 수량과 가격이 맞을때만 사게끔 되어있기때문에
// 매수 할때 조건에 맞는 매도 주문이 존재할 경우 해당 매도 주문을 채결하여 사게끔만든다.

