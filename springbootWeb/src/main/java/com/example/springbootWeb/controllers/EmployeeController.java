package com.example.springbootWeb.controllers;

import com.example.springbootWeb.dto.EmployeeDTO;
import com.example.springbootWeb.entities.EmployeeEntity;
import com.example.springbootWeb.repositories.EmployeeRepository;
import com.example.springbootWeb.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId") Long id){
       Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
      return employeeDTO
              .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
              .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee)
    {
       EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId)
    {
        return ResponseEntity.ok(employeeService.updateEmployeeId(employeeId, employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> DeleteEmployeeById(@PathVariable Long employeeId)
    {
       boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
       if(gotDeleted) return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO>  updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId)
    {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
