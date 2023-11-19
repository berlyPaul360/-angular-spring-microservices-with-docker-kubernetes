package Mapper;

import com.balloons.ProductService.dto.ProductRequestDto;
import com.balloons.ProductService.dto.ProductResponseDto;
import com.balloons.ProductService.entity.ProductEntity;

public class ProductMapper {

    public static ProductResponseDto mapEntityToProductResponseDto(ProductEntity productEntity){

        return ProductResponseDto.builder()
                        .productId(productEntity.getProductId())
                        .productName(productEntity.getProductName())
                        .quantity(productEntity.getQuantity())
                        .price(productEntity.getPrice())
                        .build();

    }
    public static ProductRequestDto mapEntityToProductRequestDto(ProductEntity productEntity){

        return ProductRequestDto.builder()
                        .productId(productEntity.getProductId())
                        .productName(productEntity.getProductName())
                        .quantity(productEntity.getQuantity())
                        .price(productEntity.getPrice())
                        .build();

    }
    public static ProductEntity mapProductRequestDtoToEntity(ProductRequestDto productRequestDto){

        return ProductEntity.builder()
                .productId(productRequestDto.getProductId())
                .productName(productRequestDto.getProductName())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .build();


    }
    public static ProductEntity mapProductResponseDtoToEntity(ProductResponseDto productResponseDto){

        return ProductEntity.builder()
                .productId(productResponseDto.getProductId())
                .productName(productResponseDto.getProductName())
                .quantity(productResponseDto.getQuantity())
                .price(productResponseDto.getPrice())
                .build();


    }

}
