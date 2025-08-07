package com.dhl.rateorderservice.dto;

import com.dhl.rateorderservice.model.ServiceLevel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record RateDto(
        UUID id,
        ServiceLevel serviceLevel,
        BigDecimal amount,
        LocalDateTime quotedAt
) {}
