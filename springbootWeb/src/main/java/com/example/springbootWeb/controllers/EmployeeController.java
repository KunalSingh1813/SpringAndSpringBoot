package com.example.springbootWeb.controllers;

import com.example.springbootWeb.dto.EmployeeDTO;
import com.example.springbootWeb.entities.EmployeeEntity;
import com.example.springbootWeb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "secret message : adygdw%326V";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeID}")
    public EmployeeEntity getEmployeeId(@PathVariable(name = "employeeId") Long id){
       return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee)
    {
       return employeeRepository.save(inputEmployee);
    }

    @PutMapping String updateEmployeeByid()
    {
        return "Hello from PUT";
    }

}
