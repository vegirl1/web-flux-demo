package com.roi.demos.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class Trade {
    private UUID id;
    private BigDecimal price;
    private BigDecimal quantity;
    private LocalDateTime settlementDate;
    private String type;
    private List<DeliveryInstruction> deliveryInstructions;
}
