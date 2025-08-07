package com.dhl.rateorderservice.controller;

import com.dhl.rateorderservice.dto.CreateOrderRequest;
import com.dhl.rateorderservice.dto.DtoMapper;
import com.dhl.rateorderservice.dto.OrderDto;
import com.dhl.rateorderservice.model.Order;
import com.dhl.rateorderservice.service.RateOrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class RateOrderController {
    private final RateOrderService service;

    public RateOrderController(RateOrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderDto createOder(@Valid @RequestBody CreateOrderRequest request){
        Order order = new Order(
                request.customerName(),
                request.destinationCountry(),
                request.packages()
        );

        Order saved = service.createOrder(order, request.serviceLevel());
        return DtoMapper.toDto(saved);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable UUID id){
         return DtoMapper.toDto( service.getOrder(id));
    }

    @GetMapping
    public Page<OrderDto> listOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderDate").descending());
        Page<Order> ordersPage = service.findAllOrders(pageable);

        // Convert each Order → OrderDto
        return ordersPage.map(DtoMapper::toDto);
    }
}
