package com.javapractice.departmentservice.service.impl;

import com.javapractice.departmentservice.DTO.DepartmentDto;
import com.javapractice.departmentservice.Repository.DepartmentRepository;
import com.javapractice.departmentservice.entity.DepartmentEntity;
import com.javapractice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        //convert department dto to department jpa entity
        DepartmentEntity departmentEntity= new DepartmentEntity(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);

        return new DepartmentDto(
                savedDepartmentEntity.getId(),
                savedDepartmentEntity.getDepartmentName(),
                savedDepartmentEntity.getDepartmentDescription(),
                savedDepartmentEntity.getDepartmentCode()
        );
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {

        DepartmentEntity byDepartmentCode = departmentRepository.findByDepartmentCode(code);

        return new DepartmentDto(
                byDepartmentCode.getId(),
                byDepartmentCode.getDepartmentName(),
                byDepartmentCode.getDepartmentDescription(),
                byDepartmentCode.getDepartmentCode()
        );
    }
}
