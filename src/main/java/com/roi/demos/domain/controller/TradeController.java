package com.roi.demos.domain.controller;

import com.roi.demos.domain.entity.Trade;
import com.roi.demos.domain.service.TradeHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "/trade")
public class TradeController {
    @Autowired
    private TradeHandlerService tradeHandlerService;

    @GetMapping(value = "/findByTradeId", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Trade> findByTradeId(@RequestParam(value = "tradeId") String tradeId)    {
        return this.tradeHandlerService.getTradeById(tradeId);
    }

    @GetMapping(value = "/findByTradeType", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Trade> findByTradeType(@RequestParam(value = "tradeType") String tradeType)    {
        return this.tradeHandlerService.getTradesByType(tradeType);
    }

    @GetMapping(value = "tradeTxtStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getTradeTxtStream() {
        return this.tradeHandlerService.getTradeStream();
    }
}
