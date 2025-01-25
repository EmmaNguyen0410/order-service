package com.emmang.order.controller;

import com.emmang.order.dto.OrderRequestDto;
import com.emmang.order.dto.OrderResponseAdminDto;
import com.emmang.order.dto.OrderResponseGuestDto;
import com.emmang.order.dto.ProductRequestDto;
import com.emmang.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/guest-access-only")
    public ResponseEntity<OrderResponseGuestDto> guestAccess(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDetailsGuest(orderRequestDto));
    }

    @PostMapping("/admin-access-only")
    public ResponseEntity<OrderResponseAdminDto> adminAccess(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDetailsAdmin(orderRequestDto));
    }

    @GetMapping("/any-role")
    public ResponseEntity<String> anyAccess() {
        return ResponseEntity.ok("Any access !");
    }

    @PostMapping("/guest-access-call-guest-access")
    public ResponseEntity<String> guestToGuestAccess(@RequestBody ProductRequestDto productRequestDto) {
        String modifiedProductName = orderService.getModifiedProductName(productRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Guest access calls guest access with modified product name: " + modifiedProductName);
    }

    @PostMapping("/guest-access-call-admin-access")
    public ResponseEntity<String> guestToAdminAccess(@RequestBody ProductRequestDto productRequestDto) {
        String modifiedProductStar = orderService.getModifiedProductStar(productRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Admin access calls guest access with modified product star: " + modifiedProductStar);
    }
}
