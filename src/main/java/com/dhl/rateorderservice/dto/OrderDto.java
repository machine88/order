package com.dhl.rateorderservice.dto;

import com.dhl.rateorderservice.model.PackageDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDto(
        UUID id,
        String customerName,
        String destinationCountry,
        LocalDateTime orderDate,
        List<PackageDetail> packages,
        List<RateDto> rates
) {}
