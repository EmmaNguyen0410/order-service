package com.emmang.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseGuestDto {
    public int getProductStar() {
        return productStar;
    }

    public void setProductStar(int productStar) {
        this.productStar = productStar;
    }

    private int productStar;
}