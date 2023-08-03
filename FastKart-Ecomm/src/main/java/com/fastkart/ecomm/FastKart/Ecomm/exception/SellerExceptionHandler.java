package com.fastkart.ecomm.FastKart.Ecomm.exception;

import com.fastkart.ecomm.FastKart.Ecomm.dto.SellerErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SellerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SellerErrorResponse> handleException(ProductException exc){
        var errorResponse = SellerErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exc.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
