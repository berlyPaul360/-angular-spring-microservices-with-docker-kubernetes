package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {


    private OrganizationService organizationService;
    //already initialized in constructor through AllArgsConstructor
    //public class OrganizationController(OrganizationService organizationService){this.organizationService = organizationService;
    //Build save Organization REST API

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){

        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);

        return new  ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    //Build Get Organization by Code REST API
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code")String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
}
