package com.javapractice.departmentservice.Repository;

import com.javapractice.departmentservice.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

        DepartmentEntity findByDepartmentCode(String departmentCode);
}
