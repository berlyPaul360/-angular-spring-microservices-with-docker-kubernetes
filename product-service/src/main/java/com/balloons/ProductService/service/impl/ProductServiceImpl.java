package com.balloons.ProductService.service.impl;

import Mapper.ProductMapper;
import com.balloons.ProductService.dto.ProductRequestDto;
import com.balloons.ProductService.dto.ProductResponseDto;
import com.balloons.ProductService.entity.ProductEntity;
import com.balloons.ProductService.exception.ProductServiceCustomException;
import com.balloons.ProductService.repository.ProductRepository;
import com.balloons.ProductService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public long addProduct(ProductRequestDto productRequest) {
        log.info("in the addProduct method of ProductServiceImpl.....Adding product to the database");

        ProductEntity productEntity
                = ProductEntity.builder()
                .productName(productRequest.getProductName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(productEntity);
        log.info("product created successfully");
        return productEntity.getProductId();
    }

    @Override
    public ProductResponseDto getProductById(long productId) {
        log.info("Get the product for productId:{}",productId);

        ProductEntity productEntity =
                productRepository.findById(productId)
                        .orElseThrow(()->new ProductServiceCustomException("Product with given id does not exist","PRODUCT_NOT_FOUND"));

        return ProductMapper.mapEntityToProductResponseDto(productEntity);
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
         log.info("Reduce Quantity {} for Id: {}", quantity,productId);

         ProductEntity productEntity
                 = productRepository.findById(productId)
                 .orElseThrow(() ->
                         new ProductServiceCustomException(
                                 "Product with given id does not exist",
                                 "PRODUCT_NOT_FOUND"));
         if(productEntity.getQuantity() < quantity) {
             throw new ProductServiceCustomException(
                     "Product does not have sufficient quantity",
                     "INSUFFICIENT QUANTITY");
         }

         productEntity.setQuantity(productEntity.getQuantity() - quantity);
         productRepository.save(productEntity);

         log.info("Product Quantity updated Successfully");
    }
}
