package com.balloonApp.paymentservice.controller;

import com.balloonApp.paymentservice.dto.PaymentRequestDto;
import com.balloonApp.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
