package com.javapractice.departmentservice.service;

import com.javapractice.departmentservice.DTO.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto department);

    DepartmentDto getDepartmentByCode(String code);
}
