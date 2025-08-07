package com.dhl.rateorderservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false, length = 2)
    private String destinationCountry;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "order_packages",
            joinColumns = @JoinColumn(name = "order_id")
    )
    private List<PackageDetail> packages;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Rate> rates;

    public Order(String customerName,
                 String destinationCountry,
                 List<PackageDetail> packages) {
        this.customerName = customerName;
        this.destinationCountry = destinationCountry;
        this.packages = packages;
    }
}
