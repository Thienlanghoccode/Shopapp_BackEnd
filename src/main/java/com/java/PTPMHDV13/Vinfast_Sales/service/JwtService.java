package com.java.PTPMHDV13.Vinfast_Sales.service;

import com.java.PTPMHDV13.Vinfast_Sales.enums.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails user);

    String extractUsername(String token, TokenType type);

    boolean isValidToken(String token,TokenType type, UserDetails user);

    String generateRefreshToken(UserDetails user);
}
