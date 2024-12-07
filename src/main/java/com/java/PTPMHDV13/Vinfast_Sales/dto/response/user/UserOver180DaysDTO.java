package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserOver180DaysDTO {
    private String username;
    private int purchaseFrequency;
    private int lastPurchaseDays;
}
