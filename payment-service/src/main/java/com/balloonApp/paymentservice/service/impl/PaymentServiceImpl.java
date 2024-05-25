package com.balloonApp.paymentservice.service.impl;

import com.balloonApp.paymentservice.dto.PaymentMode;
import com.balloonApp.paymentservice.dto.PaymentRequestDto;
import com.balloonApp.paymentservice.dto.PaymentResponse;
import com.balloonApp.paymentservice.entity.TransactionDetailsEntity;
import com.balloonApp.paymentservice.repository.TransactionDetailsRepository;
import com.balloonApp.paymentservice.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository transactRepo;
    @Override
    public Long doPayment(PaymentRequestDto paymentRequestDto) {

        log.info("Recording Payment Details: {}", paymentRequestDto);

        TransactionDetailsEntity transactEntity =
                TransactionDetailsEntity.builder()
                        .paymentDate(Instant.now())
                        .paymentMode(paymentRequestDto.getPaymentMode().name())
                        .paymentStatus("SUCCESS")
                        .orderId(paymentRequestDto.getOrderId())
                        .referenceNumber(paymentRequestDto.getReferenceNumber())
                        .amount(paymentRequestDto.getAmount())
                        .build();

        transactRepo.save(transactEntity);
        log.info("Transaction Completed with Id: {}", transactEntity.getId());

        return transactEntity.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details for the Order ID: ");

        TransactionDetailsEntity transactionDetails
                = transactRepo.findByOrderId(Long.parseLong(orderId));


                return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

    }


}
