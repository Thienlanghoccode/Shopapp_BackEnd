package com.java.PTPMHDV13.Vinfast_Sales.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be bank")
    private String email;

    @NotNull(message = "Password must not be null")
    private String password;
}
