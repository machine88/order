package com.dhl.rateorderservice.repository;

import com.dhl.rateorderservice.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RateRepository extends JpaRepository<Rate, UUID> {
}
