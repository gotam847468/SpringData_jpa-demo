package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Employee;
import com.bezkoder.spring.datajpa.model.EmployeeList;
import com.bezkoder.spring.datajpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceDelegate;

    @GetMapping("/getAll")
    public ResponseEntity<EmployeeList> getAllEmployees() {
        return employeeServiceDelegate.getAllEmployees();
    }

    @GetMapping("/getEmployeeByID/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
        return employeeServiceDelegate.getEmployeeById(id);
    }

    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id) {
        return employeeServiceDelegate.deleteEmployee(id);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(Employee emp) {
        return employeeServiceDelegate.addEmployee(emp);
    }

    @GetMapping("/getBySalary/{lb}/{ub}")
    public ResponseEntity<List<Employee>> getEmpSalary(@PathVariable("lb") double lb, @PathVariable("ub") double ub) {
        return employeeServiceDelegate.getEmpSalary(lb, ub);
    }
}
