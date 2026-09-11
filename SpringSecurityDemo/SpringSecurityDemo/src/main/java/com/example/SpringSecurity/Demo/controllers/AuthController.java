package com.example.SpringSecurity.Demo.controllers;

import com.example.SpringSecurity.Demo.dto.LoginDTO;
import com.example.SpringSecurity.Demo.dto.SignupDTO;
import com.example.SpringSecurity.Demo.dto.UserDTO;
import com.example.SpringSecurity.Demo.service.AuthService;
import com.example.SpringSecurity.Demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignupDTO signupDto){
        UserDTO userDto = userService.signUp(signupDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto, HttpServletRequest request, HttpServletResponse response){
        String token = authService.login(loginDto);

        //Paasing HTTP Only cookie from backend
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true); //make sure that cookie cannot be accessed by any other means other than HTTP Methods anc cannot be accessed by javascript

        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
