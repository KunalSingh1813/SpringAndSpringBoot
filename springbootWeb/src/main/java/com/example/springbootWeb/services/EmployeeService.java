package com.example.springbootWeb.services;

import com.example.springbootWeb.dto.EmployeeDTO;
import com.example.springbootWeb.entities.EmployeeEntity;
import com.example.springbootWeb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity , EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
       EmployeeEntity saveEmployeeEntity = employeeRepository.save(toSaveEntity);
       return modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        try {
            boolean exists = employeeRepository.existsById(employeeId);
            if(!exists) return false;
            employeeRepository.deleteById(employeeId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
