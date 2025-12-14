package com.example.springbootWeb.controllers;

import com.example.springbootWeb.dto.EmployeeDTO;
import com.example.springbootWeb.entities.EmployeeEntity;
import com.example.springbootWeb.repositories.EmployeeRepository;
import com.example.springbootWeb.services.EmployeeService;
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

   private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{employeeID}")
    public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId") Long id){
       return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee)
    {
       return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping(path="/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId)
    {
        return employeeService.updateEmployeeId(employeeId, employeeDTO);
    }

    @DeleteMapping(path="/{employeeId}")
    public void DeleteEmployeeById(@PathVariable Long employeeId)
    {
        employeeService.deleteEmployeeById(employeeId);
    }

}
