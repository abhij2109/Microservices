package com.abhi.user.service.exception;

import com.abhi.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public  ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException nex){
        String message=nex.getMessage();

        ApiResponse response = ApiResponse
                .builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        ApiResponse response1 = ApiResponse
                .builder()
                .message(message)
                .success(false)
                .status(HttpStatus.CREATED)
                .build();

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
