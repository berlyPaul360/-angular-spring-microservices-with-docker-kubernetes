package com.bal_decor.OrderService.service;

import com.bal_decor.OrderService.dto.OrderRequestDto;
import com.bal_decor.OrderService.dto.OrderResponseDto;

public interface OrderService {
    long placeOrder(OrderRequestDto orderRequest);

    OrderResponseDto getOrderDetails(long orderId);
}
