package com.emmang.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class OrderController {
    @PostMapping("/hi")
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok("hi order");
    }
}
