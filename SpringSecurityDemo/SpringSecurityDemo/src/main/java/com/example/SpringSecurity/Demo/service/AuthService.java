package com.example.SpringSecurity.Demo.service;

import com.example.SpringSecurity.Demo.dto.LoginDTO;
import com.example.SpringSecurity.Demo.dto.LoginResponseDto;
import com.example.SpringSecurity.Demo.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    public LoginResponseDto login(LoginDTO loginDto) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail() , loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String AccessToken;
        AccessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new LoginResponseDto(user.getId(),AccessToken,refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshtoken) {
        Long userId = jwtService.getUserIdFromToken(refreshtoken);
        User user = userService.getUserById(userId);

        String AccessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(), AccessToken, refreshtoken);

    }
}
