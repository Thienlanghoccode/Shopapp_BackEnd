package com.java.PTPMHDV13.Vinfast_Sales.dto.response.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TopSellingProduct {
    private Integer productId;
    private String productName;
    private Integer totalQuantity;
    private BigDecimal totalRevenue;
}
