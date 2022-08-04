package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
