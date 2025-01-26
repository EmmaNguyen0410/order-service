package com.emmang.order.controller;

import com.emmang.order.dto.OrderRequestDto;
import com.emmang.order.dto.OrderResponseAdminDto;
import com.emmang.order.dto.OrderResponseGuestDto;
import com.emmang.order.dto.ProductRequestDto;
import com.emmang.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/any-role")
    public ResponseEntity<String> anyAccess() {
        return ResponseEntity.ok("Any access !");
    }

    @PostMapping("/guest-access-call-admin-access")
    public ResponseEntity<String> guestToAdminAccess(@RequestBody ProductRequestDto productRequestDto) {
        String res = orderService.getModifiedProductName(productRequestDto);
        return ResponseEntity.ok("Modified product name: " + res);
    }

    @PostMapping("/guest-access-call-guest-access")
    public ResponseEntity<String> guestToGuestAccess(@RequestBody ProductRequestDto productRequestDto) {
        String res = orderService.getModifiedProductStar(productRequestDto);
        return ResponseEntity.ok("Modified product star: " + res);
    }

    @PostMapping("/admin-access-only")
    public ResponseEntity<OrderResponseAdminDto> adminAccess(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseAdminDto orderResponseAdminDto = orderService.getOrderDetailsAdmin(orderRequestDto);
        return ResponseEntity.ok(orderResponseAdminDto);
    }

    @PostMapping("/guest-access-only")
    public ResponseEntity<OrderResponseGuestDto> guestAccess(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseGuestDto orderResponseGuestDto = orderService.getOrderDetailsGuest(orderRequestDto);
        return ResponseEntity.ok(orderResponseGuestDto);
    }
}
