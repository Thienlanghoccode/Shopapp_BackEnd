package com.java.PTPMHDV13.Vinfast_Sales.dto.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRFMAnalysisDTO {
    private String username;
    private String recencySegment;
    private String frequencySegment;
    private String monetarySegment;
}
