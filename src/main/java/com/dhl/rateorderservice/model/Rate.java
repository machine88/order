package com.dhl.rateorderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rates")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ServiceLevel serviceLevel;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime quotedAt = LocalDateTime.now();

    public Rate(Order order,
                ServiceLevel serviceLevel,
                BigDecimal amount) {
        this.order = order;
        this.serviceLevel = serviceLevel;
        this.amount = amount;
    }
}
