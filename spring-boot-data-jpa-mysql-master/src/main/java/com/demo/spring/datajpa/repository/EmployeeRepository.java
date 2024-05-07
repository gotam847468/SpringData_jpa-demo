package com.demo.spring.datajpa.repository;

import com.demo.spring.datajpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
