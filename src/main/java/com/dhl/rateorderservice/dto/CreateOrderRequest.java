package com.dhl.rateorderservice.dto;

import com.dhl.rateorderservice.model.PackageDetail;
import com.dhl.rateorderservice.model.ServiceLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateOrderRequest(
        @NotBlank
        String customerName,

        @NotBlank(message = "must not be blank.")
        @Size(min = 2, max = 2, message = "should only include 2 letters.")
        String destinationCountry,

        @NotNull
        ServiceLevel serviceLevel,

        @NotEmpty(message = "must not be empty.")
        List<@Valid PackageDetail> packages
) {}