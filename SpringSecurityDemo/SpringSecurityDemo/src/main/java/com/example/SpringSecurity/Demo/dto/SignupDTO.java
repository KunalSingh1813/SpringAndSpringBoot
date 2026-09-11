package com.example.SpringSecurity.Demo.dto;

import lombok.Data;

@Data
public class SignupDTO {

    //Validators need to be added.
    private String email;
    private String password;
    private String name;
}
