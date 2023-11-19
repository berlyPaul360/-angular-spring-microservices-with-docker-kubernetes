package com.balloons.ProductService.controller;
import com.balloons.ProductService.dto.ProductRequestDto;
import com.balloons.ProductService.dto.ProductResponseDto;
import com.balloons.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequestDto productRequestDto){

        long productId = productService.addProduct(productRequestDto);

        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProductsById(@PathVariable("id") long productId){
        ProductResponseDto productResponseDto =
                productService.getProductById(productId);

        return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ){
        productService.reduceQuantity(productId, quantity);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
