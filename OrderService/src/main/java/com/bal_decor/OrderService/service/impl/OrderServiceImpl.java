package com.bal_decor.OrderService.service.impl;

import com.bal_decor.OrderService.dto.OrderRequestDto;
import com.bal_decor.OrderService.entity.OrderEntity;
import com.bal_decor.OrderService.externel.client.ProductService;
import com.bal_decor.OrderService.repository.OrderRepository;
import com.bal_decor.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequestDto orderRequest) {
        log.info("*******************Inside the placeOrder() method from OrderService********************************");
        //Order Entity -> Save the data with Status Order Created
        //Product Service - Block Products (Reduce the Quantity)
        //Payment Service -> Payments -> Success -> COMPLETE, Else
        //CANCELED

        log.info("Placing Order Request:: {}",orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Creating Order with Status CREATED");

        OrderEntity orderEntity = OrderEntity.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderData(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        orderEntity = orderRepository.save(orderEntity);
        log.info("Order Places successfully with Order Id: {}",orderEntity.getId());
        return orderEntity.getId();
    }
}
