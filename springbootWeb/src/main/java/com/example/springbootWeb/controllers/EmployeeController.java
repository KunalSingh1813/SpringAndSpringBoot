package com.example.springbootWeb.controllers;

import com.example.springbootWeb.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "secret message : adygdw%326V";
//    }


    @GetMapping(path = "/{employeeID}")
    public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id, "Kunal", "kunal@gmail.com", 24 , LocalDate.of(2024,1,2),true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age "+age+" "+sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee)
    {
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping String updateEmployeeByid()
    {
        return "Hello from PUT";
    }

}
