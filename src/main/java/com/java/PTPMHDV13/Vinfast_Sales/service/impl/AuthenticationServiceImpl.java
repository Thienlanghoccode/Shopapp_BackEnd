package com.java.PTPMHDV13.Vinfast_Sales.service.impl;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.SignInRequest;
import com.java.PTPMHDV13.Vinfast_Sales.dto.response.TokenResponse;
import com.java.PTPMHDV13.Vinfast_Sales.entity.User;
import com.java.PTPMHDV13.Vinfast_Sales.repository.UserRepository;
import com.java.PTPMHDV13.Vinfast_Sales.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.java.PTPMHDV13.Vinfast_Sales.enums.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public TokenResponse authenticate(SignInRequest request) {
        log.info("-------------------Authenticating---------------------");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username or password is incorrect"));

        //create new access token
        String accessToken = jwtService.generateToken(user);

        //create new refresh token
        String refreshToken = jwtService.generateRefreshToken(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    public TokenResponse refresh(HttpServletRequest request) {
        log.info("-------------------Refresh Token---------------------");
        String refreshToken = request.getHeader("x-token");
        if(StringUtils.isBlank(refreshToken)) {
            throw new AuthenticationServiceException("Invalid token");
        }

        //extract user from token
        final String username = jwtService.extractUsername(refreshToken, REFRESH_TOKEN);

        //check into db
        Optional<User> user = userRepository.findByUsername(username);
        if(!jwtService.isValidToken(refreshToken, REFRESH_TOKEN, user.get()))
            throw new AuthenticationServiceException("Invalid token");
        String newAccessToken = jwtService.generateRefreshToken(user.get());
        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .userId(user.get().getId())
                .build();
    }
}
