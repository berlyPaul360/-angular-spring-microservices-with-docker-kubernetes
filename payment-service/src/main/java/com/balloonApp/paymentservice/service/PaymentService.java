package com.balloonApp.paymentservice.service;

import com.balloonApp.paymentservice.dto.PaymentRequestDto;
import com.balloonApp.paymentservice.dto.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequestDto paymentRequestDto);


    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
