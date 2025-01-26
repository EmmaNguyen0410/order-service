package com.emmang.order.service;

import com.emmang.order.client.ProductServiceClient;
import com.emmang.order.dto.*;
import com.emmang.order.exception.RestTemplateFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class OrderService {
    private final ProductServiceClient productServiceClient;

    public OrderService(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public OrderResponseAdminDto getOrderDetailsAdmin(OrderRequestDto orderRequestDto) {
        OrderResponseAdminDto response = new OrderResponseAdminDto();
        response.setOrderId(orderRequestDto.getOrderId());
        response.setName("Cup");
        response.setPrice(20);
        return response;
    }

    public OrderResponseGuestDto getOrderDetailsGuest(OrderRequestDto orderRequestDto) {
        OrderResponseGuestDto response = new OrderResponseGuestDto();
        response.setQty(20);
        return response;
    }

    public String getModifiedProductName(ProductRequestDto productRequestDto) {
        try {
            return productServiceClient.fetchProductAdmin(productRequestDto).getName() + "1";
        } catch (HttpStatusCodeException e) {
            throw new RestTemplateFailureException(e.getMessage());
        }
    }

    public String getModifiedProductStar(ProductRequestDto productRequestDto) {
        try {
            return productServiceClient.fetchProductGuest(productRequestDto).getProductStar() + 1 + "";
        } catch (HttpStatusCodeException e) {
            throw new RestTemplateFailureException(e.getMessage());
        }
    }

}