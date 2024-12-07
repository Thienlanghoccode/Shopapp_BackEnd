package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderBehaviorAnalysisDTO {
    private String username;
    private Integer totalOrders;
    private Integer averageItemsPerOrder;
}
