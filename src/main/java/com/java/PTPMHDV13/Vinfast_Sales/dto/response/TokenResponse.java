package com.java.PTPMHDV13.Vinfast_Sales.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;

    private String refreshToken;

    private Long userId;
}
