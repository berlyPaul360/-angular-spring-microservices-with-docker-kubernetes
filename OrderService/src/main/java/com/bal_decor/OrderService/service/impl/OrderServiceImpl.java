package com.bal_decor.OrderService.service.impl;

import com.bal_decor.OrderService.dto.OrderRequestDto;
import com.bal_decor.OrderService.dto.OrderResponseDto;
import com.bal_decor.OrderService.dto.ProductResponseDto;
import com.bal_decor.OrderService.entity.OrderEntity;
import com.bal_decor.OrderService.exception.CustomException;
import com.bal_decor.OrderService.externel.PaymentResponse;
import com.bal_decor.OrderService.externel.client.PaymentService;
import com.bal_decor.OrderService.externel.client.ProductService;
import com.bal_decor.OrderService.externel.requests.PaymentRequestDto;
import com.bal_decor.OrderService.repository.OrderRepository;
import com.bal_decor.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequestDto orderRequest) {
        log.info("*******************Inside the placeOrder() method from OrderService********************************");
        //Order Entity -> Save the data with Status Order Created
        //Product Service - Block Products (Reduce the Quantity)
        // Payment Service -> Payments -> Success -> COMPLETE, Else
        //@TODO CANCELED

        log.info("Placing Order Request:: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating Order with Status CREATED");

        OrderEntity orderEntity = OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderData(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        orderEntity = orderRepository.save(orderEntity);

        log.info("Calling Payment Service to complete the payment: {}", orderEntity.getId());
        PaymentRequestDto paymentRequestDto =
                PaymentRequestDto.builder()
                        .orderId(orderEntity.getId())
                        .paymentMode(orderRequest.getPaymentMode())
                        .amount(orderRequest.getTotalAmount())
                        .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequestDto);
            log.info("Payment done SUCCESSFUL. Changing the Order Status to placed");
            orderStatus = "PLACED";

        } catch (Exception e) {
            log.error("Error occurred in payment. Changing order status to FAILED");
            orderStatus = "PAYMENT_FAILED";
        }
        orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);
        log.info("Order Places successfully with Order Id: {}", orderEntity.getId());
        return orderEntity.getId();
    }

    @Override
    public OrderResponseDto getOrderDetails(long orderId) {
        log.info("Get Order Details for Order Id : {}", orderId);

        OrderEntity orderEntity
                = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found for the order Id: " + orderId
                        , "NOT_FOUND", 404));
        ProductResponseDto productResponse = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/" + orderEntity.getProductId(),
                ProductResponseDto.class
        );
        log.info("Getting payment information from the Payment Service");

                PaymentResponse paymentResponse
                 =restTemplate.getForObject(
                "http://PAYMENT-SERVICE/payment/order/" + orderEntity.getId(),
                PaymentResponse.class
         );

        OrderResponseDto.ProductDetails productDetails
                = OrderResponseDto.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();

        OrderResponseDto.PaymentDetails paymentDetails
                = OrderResponseDto.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .status(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponseDto orderResponse
                = OrderResponseDto.builder()
                .orderId(orderEntity.getId())
                .orderStatus(orderEntity.getOrderStatus())
                .orderDate(orderEntity.getOrderData())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .amount(orderEntity.getAmount())
                .build();


        return orderResponse;
    }

}