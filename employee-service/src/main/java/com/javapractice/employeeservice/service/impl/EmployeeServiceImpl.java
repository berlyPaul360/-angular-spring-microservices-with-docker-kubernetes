package com.javapractice.employeeservice.service.impl;

import com.javapractice.employeeservice.dto.APIResponseDto;
import com.javapractice.employeeservice.dto.DepartmentDto;
import com.javapractice.employeeservice.dto.EmployeeDto;
import com.javapractice.employeeservice.entity.EmployeeEntity;
import com.javapractice.employeeservice.exception.ResourceNotFoundException;
import com.javapractice.employeeservice.repository.EmployeeRepo;
import com.javapractice.employeeservice.service.EmployeeService;
import com.javapractice.employeeservice.dto.OrganizationDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;
    private WebClient webClient;
    private APIClient feignClient;
    //private RestTemplate restTemplate;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );

        EmployeeEntity saveEmployee= employeeRepo.save(employeeEntity);

        return modelMapper.map(saveEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long id){
        EmployeeEntity employeeEntity = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","employeeId",id.toString()));
        //ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/department/" + employeeEntity.getDepartmentCode(), DepartmentDto.class);
       // DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/department/" + employeeEntity.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = feignClient.getDepartment(employeeEntity.getDepartmentCode());
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employeeEntity.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        return apiResponseDto;
    }
}
