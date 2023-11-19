package com.bal_decor.OrderService.service;

import com.bal_decor.OrderService.dto.OrderRequestDto;

public interface OrderService {
    long placeOrder(OrderRequestDto orderRequest);
}
