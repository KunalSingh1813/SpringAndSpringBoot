package com.example.springbootWeb.dto;

import com.example.springbootWeb.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
  private  Long id;

  @NotBlank(message="Name cannot be blank")
  @Size(min=3, max=10, message = "Number of characters in name should be in range [3,10]")
  private String name;

  @Email(message = "it should be a valid email")
  private  String email;

  @NotNull(message = "Age of employee cannot be blank")
  @Max(value = 80, message = "Age of Employee cannot be greater than 80")
  @Min(value = 18, message = "Age of Employee Cannot be less than 18")
  private  Integer age;

  @NotBlank(message = "Role of the employee cannot be blank")
  //@Pattern(regexp = "^(ADMIN|USER)$" ,message = "Role of user can either be USER or ADMIN")
  @EmployeeRoleValidation //Custom Annotation
  private String role; //ADMIN, USER


  @Positive(message = "Salary of Employee should be positive")
  @NotNull(message = "Salary of Employee should not be null")
  @Digits(integer = 6,fraction = 2,message = "The Salary Can be in the form XXXXXX.YY")
  @DecimalMin(value="100000.99")
  @DecimalMax(value="500000.99")
  private Integer Salary;

  @PastOrPresent(message = "Date of joining cannot be future date.")
  private  LocalDate dateOfJoining;

  @AssertTrue(message="Employee should be active")
  @JsonProperty("isActive")
  private Boolean isActive;


//   //All argument constructor
//    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
//
//    //Default Constructor
//    public EmployeeDTO(){
//
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
}
