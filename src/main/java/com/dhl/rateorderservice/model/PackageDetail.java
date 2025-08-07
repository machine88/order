package com.dhl.rateorderservice.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PackageDetail {

    @NotNull
    @Positive(message = "Weight must be > 0")
    private Double weightKg;

    @NotNull
    @Positive(message = "Length must be > 0")
    private Double lengthCm;

    @NotNull
    @Positive(message = "With must be > 0")
    private Double widthCm;

    @NotNull
    @Positive(message = "Height must be > 0")
    private Double heightCm;
}
