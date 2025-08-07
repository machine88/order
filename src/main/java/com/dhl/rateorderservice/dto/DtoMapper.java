package com.dhl.rateorderservice.dto;

import com.dhl.rateorderservice.model.Order;
import com.dhl.rateorderservice.model.Rate;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    public static RateDto toDto(Rate rate) {
        return new RateDto(
                rate.getId(),
                rate.getServiceLevel(),
                rate.getAmount(),
                rate.getQuotedAt()
        );
    }

    public static OrderDto toDto(Order order) {
        List<RateDto> rateDtos = order.getRates().stream()
                .map(DtoMapper::toDto)
                .collect(Collectors.toList());

        return new OrderDto(
                order.getId(),
                order.getCustomerName(),
                order.getDestinationCountry(),
                order.getOrderDate(),
                order.getPackages(),
                rateDtos
        );
    }
}
