package com.java.PTPMHDV13.Vinfast_Sales.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductRevenueDTO {
    private Integer productId;
    private String productName;
    private BigDecimal totalRevenue;
}
