package com.example.springbootWeb.repositories;

import com.example.springbootWeb.advices.ApiError;
import com.example.springbootWeb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
