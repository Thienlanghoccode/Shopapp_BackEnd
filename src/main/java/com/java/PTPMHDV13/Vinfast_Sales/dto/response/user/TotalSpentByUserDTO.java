package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TotalSpentByUserDTO {
    private String username;
    private BigDecimal totalSpent;
}
