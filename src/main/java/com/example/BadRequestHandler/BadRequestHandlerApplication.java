package com.example.BadRequestHandler;

import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;

@RestController
@SpringBootApplication
public class BadRequestHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadRequestHandlerApplication.class, args);
    }

    @PostMapping("test")
    public void getName(
            @ApiParam(value = "JSON representation of ShipmentResponse", required = true, name = "shipmentResponseJson")
            @Valid @RequestBody String shipmentResponseJson) {
        System.out.println("Success!" + shipmentResponseJson);
    }
}

@ControllerAdvice
class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        System.out.println(ex);
        return ResponseEntity.badRequest().body("YOUR REQUEST PARAMS NOT MATCH!");
    }
}
