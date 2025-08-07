package com.dhl.rateorderservice.repository;

import com.dhl.rateorderservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  OrderRepository extends JpaRepository<Order, UUID> {
}
