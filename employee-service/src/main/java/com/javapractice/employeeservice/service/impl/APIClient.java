package com.javapractice.employeeservice.service.impl;

import com.javapractice.employeeservice.dto.DepartmentDto;
import com.javapractice.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/department/{department-code}")
   DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);


}