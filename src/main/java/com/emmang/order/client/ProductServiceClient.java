package com.emmang.order.client;

import com.emmang.order.constant.RestEndpoint;
import com.emmang.order.dto.ProductRequestDto;
import com.emmang.order.dto.ProductResponseAdminDto;
import com.emmang.order.dto.ProductResponseGuestDto;
import com.emmang.order.entity.UserDetailsImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductResponseAdminDto fetchProductAdmin(ProductRequestDto productRequestDto) {
        return restTemplate.exchange(
                RestEndpoint.PRODUCT_ADMIN_ACCESS_ONLY_URL,
                HttpMethod.POST,
                new HttpEntity<>(productRequestDto, getHeaders()),
                ProductResponseAdminDto.class
        ).getBody();
    }

    public ProductResponseGuestDto fetchProductGuest(ProductRequestDto productRequestDto) {
        return restTemplate.exchange(
                RestEndpoint.PRODUCT_GUEST_ACCESS_ONLY_URL,
                HttpMethod.POST,
                new HttpEntity<>(productRequestDto, getHeaders()),
                ProductResponseGuestDto.class
        ).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = ((UserDetailsImpl) authentication.getPrincipal()).getAuthorities().get(0).getAuthority();
        String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
        httpHeaders.add("username", username);
        httpHeaders.add("role", role);
        return httpHeaders;
    }
}