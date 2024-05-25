package com.balloonApp.paymentservice.controller;

import com.balloonApp.paymentservice.dto.PaymentRequestDto;
import com.balloonApp.paymentservice.dto.PaymentResponse;
import com.balloonApp.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequestDto paymentRequestDto){

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequestDto),
                HttpStatus.OK
         );

    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(String orderId){

        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }
}
