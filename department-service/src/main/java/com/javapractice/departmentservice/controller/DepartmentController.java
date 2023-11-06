package com.javapractice.departmentservice.controller;

import com.javapractice.departmentservice.DTO.DepartmentDto;
import com.javapractice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/department")
@AllArgsConstructor
public class DepartmentController {

    
    private DepartmentService departmentService;
    
    //Build save department rest api 

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        return ResponseEntity.ok(savedDepartmentDto);
    }

    //Build get department rest api
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code")String departmentCode){

        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
    
}
