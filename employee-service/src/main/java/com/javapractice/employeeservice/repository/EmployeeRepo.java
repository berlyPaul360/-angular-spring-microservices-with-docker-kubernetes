package com.javapractice.employeeservice.repository;

import com.javapractice.employeeservice.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
}
