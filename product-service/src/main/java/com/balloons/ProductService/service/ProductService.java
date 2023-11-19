package com.balloons.ProductService.service;
import com.balloons.ProductService.dto.ProductRequestDto;
import com.balloons.ProductService.dto.ProductResponseDto;

public interface ProductService {
    long addProduct(ProductRequestDto productRequest);

    ProductResponseDto getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
