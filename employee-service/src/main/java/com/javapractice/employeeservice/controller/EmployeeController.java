package com.javapractice.employeeservice.controller;

import com.javapractice.employeeservice.dto.APIResponseDto;
import com.javapractice.employeeservice.dto.EmployeeDto;
import com.javapractice.employeeservice.exception.ErrorDetails;
import com.javapractice.employeeservice.exception.ResourceNotFoundException;
import com.javapractice.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {


    private EmployeeService employeeService;


    //Build will save an employee
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    //Get will get an employee
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("id") Long employeeId){

        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);

        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException e, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails (
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false),
                "User not found"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
