package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActiveUsersByMonthDTO {
    private int year;
    private int month;
    private int activeUsers;
}
