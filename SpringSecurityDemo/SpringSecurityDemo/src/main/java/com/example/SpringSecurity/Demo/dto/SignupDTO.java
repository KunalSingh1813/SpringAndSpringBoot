package com.example.SpringSecurity.Demo.dto;

import com.example.SpringSecurity.Demo.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDTO {

    //Validators need to be added.
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
}
