package com.balloonApp.paymentservice.service;

import com.balloonApp.paymentservice.dto.PaymentRequestDto;

public interface PaymentService {
    Long doPayment(PaymentRequestDto paymentRequestDto);
}
