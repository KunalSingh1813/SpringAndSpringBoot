package com.example.SpringSecurity.Demo.service;

import com.example.SpringSecurity.Demo.dto.LoginDTO;
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
    public String login(LoginDTO loginDto) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail() , loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token;
        token = jwtService.generateToken(user);
        return  token;
    }

}
