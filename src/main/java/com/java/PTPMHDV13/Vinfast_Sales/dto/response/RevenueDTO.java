package com.java.PTPMHDV13.Vinfast_Sales.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RevenueDTO {
    private String month;
    private BigDecimal totalRevenue;
}
