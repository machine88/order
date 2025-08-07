package com.dhl.rateorderservice.service;

import com.dhl.rateorderservice.exception.OrderNotFoundException;
import com.dhl.rateorderservice.model.Order;
import com.dhl.rateorderservice.model.PackageDetail;
import com.dhl.rateorderservice.model.Rate;
import com.dhl.rateorderservice.model.ServiceLevel;
import com.dhl.rateorderservice.repository.OrderRepository;
import com.dhl.rateorderservice.repository.RateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class RateOrderService {
    private final OrderRepository orderRepository;
    private final RateRepository rateRepository;

    public RateOrderService(OrderRepository orderRepository, RateRepository rateRepository) {
        this.orderRepository = orderRepository;
        this.rateRepository = rateRepository;
    }

    /**
     * 1) Persists the Order
     * 2) Calculates the shipping rate based on total weight and service level
     * 3) Persists a Rate record linked to the Order
     * 4) Attaches the Rate to the Order and returns it
     */
    public Order createOrder(Order order, ServiceLevel serviceLevel){

        // Persist the bare order
        Order saved = orderRepository.save(order);

        // Compute total weight
        double totalWeight = order.getPackages().stream().
                mapToDouble(PackageDetail::getWeightKg).
                sum();

        // What is the rate factor
        double factor = switch (serviceLevel){
            case STANDARD -> 5.0;
            case EXPRESS -> 10.0;
            case SAME_DAY -> 15.0;
        };

        // Calculate amount
        BigDecimal amount = BigDecimal.valueOf(factor * totalWeight);

        // persist rate
        Rate rate = new Rate(saved,serviceLevel,amount);
        rateRepository.save(rate);

        // Attach rate to order
        saved.setRates(List.of(rate));
        return saved;
    }

    public Order getOrder(UUID id){
        return orderRepository.findById(id).
                orElseThrow(()->new OrderNotFoundException(id));
    }
    public Page<Order> findAllOrders(Pageable pageable){
        return orderRepository.findAll(pageable);
    }
}
