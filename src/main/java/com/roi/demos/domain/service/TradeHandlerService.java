package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.Trade;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TradeHandlerService {
    Mono<Trade> getTradeById(String id);
    Flux<Trade> getTradesByType(String type);

    Flux<Trade> findAllTrades();
    void saveTrade(Trade trade);

    Flux<ServerSentEvent> getTradeStream();
}
