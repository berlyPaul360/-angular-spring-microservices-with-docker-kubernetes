package com.bal_decor.OrderService.externel.decoder;

import com.bal_decor.OrderService.exception.CustomException;
import com.bal_decor.OrderService.externel.reponse.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper
                = new ObjectMapper();

        log.info(":: {}",response.request().url());
        log.info(":: {}",response.request().headers());
        CustomException customException;

        try {
            ErrorResponseDto errorResponse
                    = objectMapper.readValue(response.body().asInputStream(),
                    ErrorResponseDto.class);

            customException = new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());

        } catch (IOException e) {
            throw new CustomException("Internal Server Error","INTERNAL_ERROR",500);
        }

        return customException;
    }
}
