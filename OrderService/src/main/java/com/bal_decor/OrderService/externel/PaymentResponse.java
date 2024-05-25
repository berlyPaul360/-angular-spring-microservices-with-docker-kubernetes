package com.bal_decor.OrderService.externel;

import com.bal_decor.OrderService.dto.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private Instant paymentDate;
    private long orderId;
    private long amount;
}
