package com.bal_decor.OrderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

    private long productId;
    private long totalAmount;
    private long quantity;
    private PaymentMode paymentMode;
}
