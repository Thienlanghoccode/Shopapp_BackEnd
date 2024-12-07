package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CustomerInteractionDTO {
    private String productName;
    private int uniqueVisitors;
    private int quantitySold;
    private BigDecimal totalRevenue;
}
