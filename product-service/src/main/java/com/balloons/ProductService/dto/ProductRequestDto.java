package com.balloons.ProductService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    private String productName;
    private long productId;
    private long quantity;
    private long price;

}
