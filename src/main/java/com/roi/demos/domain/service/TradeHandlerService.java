package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.Trade;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TradeHandlerService {
    Mono<Trade> getTradeById(String id);

    Flux<Trade> getTradesByType(String type);

    Flux<Trade> findAllTrades();

    void saveTrade(Mono<Trade> trade);

    Flux<ServerSentEvent> getTradeStream();

    Mono<Trade> findTradeByDescription(String description);
}
