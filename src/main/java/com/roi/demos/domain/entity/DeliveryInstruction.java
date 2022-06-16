package com.roi.demos.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class DeliveryInstruction {
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String country;

}
