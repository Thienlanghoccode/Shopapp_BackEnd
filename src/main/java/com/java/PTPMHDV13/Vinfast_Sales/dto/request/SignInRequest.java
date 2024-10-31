package com.java.PTPMHDV13.Vinfast_Sales.dto.request;

import com.java.PTPMHDV13.Vinfast_Sales.enums.Platform;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {

    @NotBlank(message = "Username must not be blank !")
    private String username;

    @NotBlank(message = "Password must not be blank !")
    private String password;

    @NotBlank(message = "Platform must not be blank !")
    private Platform platform;

    private String deviceToken;

    private String version;
}
