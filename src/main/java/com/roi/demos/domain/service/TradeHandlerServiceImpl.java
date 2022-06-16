package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.Trade;
import com.roi.demos.domain.repository.StubDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeHandlerServiceImpl implements TradeHandlerService {

    private final StubDataService stubDataService;

    @Override
    public Mono<Trade> getTradeById(String id) {
        var trade = stubDataService.getAllTrades().stream()
            .filter(trade1 -> StringUtils.equals(trade1.getId().toString(), id))
            .findFirst();
        if (trade.isPresent()) {
            return Mono.just(trade.get());
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Flux<Trade> getTradesByType(String type) {
        return Flux.fromIterable(stubDataService.getAllTrades().stream()
            .filter(trade -> StringUtils.equalsIgnoreCase(trade.getType(), type))
            .collect(Collectors.toList()));
    }

    @Override
    public Flux<Trade> findAllTrades() {
        return Flux.fromIterable(stubDataService.getAllTrades());
    }

    @Override
    public void saveTrade(Mono<Trade> trade) {
        log.warn("Save Trade -> not supported yet");
    }

    @Override
    public Flux<ServerSentEvent> getTradeStream() {
        return Flux.fromStream(stubDataService.getAllTrades().stream()
            .map(trade -> ServerSentEvent.builder()
                .id(UUID.randomUUID().toString())
                .event("Trade SSE - " + LocalTime.now().toString())
                .data(trade)
                .build()));
    }

    @Override
    public Mono<Trade> findTradeByDescription(String description) {
        log.info("Input arg: " + description);
        var trade = stubDataService.getAllTrades().stream()
            .filter(trade1 -> StringUtils.containsAnyIgnoreCase(trade1.getDescription().toString(), description))
            .findFirst();
        if (trade.isPresent()) {
            return Mono.just(trade.get());
        } else {
            return Mono.empty();
        }
    }
}
