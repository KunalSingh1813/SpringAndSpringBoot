package com.example.SpringSecurity.Demo.controllers;

import com.example.SpringSecurity.Demo.dto.LoginDTO;
import com.example.SpringSecurity.Demo.dto.LoginResponseDto;
import com.example.SpringSecurity.Demo.dto.SignupDTO;
import com.example.SpringSecurity.Demo.dto.UserDTO;
import com.example.SpringSecurity.Demo.service.AuthService;
import com.example.SpringSecurity.Demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupDTO signupDto){
        UserDTO userDto = userService.signUp(signupDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDTO loginDto, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        //Paasing HTTP Only cookie from backend
        Cookie cookie = new Cookie("refreshToken",loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true); //make sure that cookie cannot be accessed by any other means other than HTTP Methods anc cannot be accessed by javascript
        cookie.setSecure("production".equals(deployEnv));  // Use in HTTPS scenario.
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
       //Cookie[] cookies =  request.getCookies();
        String refreshtoken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside cookies"));
        LoginResponseDto loginResponseDto =  authService.refreshToken(refreshtoken);
      return ResponseEntity.ok(loginResponseDto);

    }
}
