package com.roi.demos.domain.repository;

import com.roi.demos.domain.entity.DeliveryInstruction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryInstructionRepository extends ReactiveCrudRepository<DeliveryInstruction, UUID> {
}
