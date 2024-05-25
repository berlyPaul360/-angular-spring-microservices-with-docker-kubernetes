package com.bal_decor.OrderService.controller;


import com.bal_decor.OrderService.dto.OrderRequestDto;
import com.bal_decor.OrderService.dto.OrderResponseDto;
import com.bal_decor.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequestDto orderRequest){

        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id: {}",orderId);

        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderDetails(@PathVariable long orderId){

        OrderResponseDto orderResponseDto
                = orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);

    }
}
