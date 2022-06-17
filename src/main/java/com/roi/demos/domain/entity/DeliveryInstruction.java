package com.roi.demos.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table("deliveryInstruction")
public class DeliveryInstruction {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String country;

}
