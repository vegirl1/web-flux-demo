package com.roi.demos.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Trade {
    private UUID id;
    private String description;
    private BigDecimal price;
    private BigDecimal quantity;
    private LocalDateTime settlementDate;
    private String type;
    private List<DeliveryInstruction> deliveryInstructions;
}
