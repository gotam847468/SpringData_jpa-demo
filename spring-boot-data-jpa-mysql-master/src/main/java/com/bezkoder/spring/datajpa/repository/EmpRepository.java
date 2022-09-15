package com.bezkoder.spring.datajpa.repository;
import com.bezkoder.spring.datajpa.model.EmployeeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<EmployeeTemplate, Long> {

}