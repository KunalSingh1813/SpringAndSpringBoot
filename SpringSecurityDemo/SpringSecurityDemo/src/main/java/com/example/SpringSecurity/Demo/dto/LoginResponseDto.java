package com.example.SpringSecurity.Demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long Id;
    private String refreshToken;
    private String accessToken;
}
