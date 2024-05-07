package com.demo.spring.datajpa.controller;

import com.demo.spring.datajpa.model.Employee;
import com.demo.spring.datajpa.model.EmployeeList;
import com.demo.spring.datajpa.services.EmployeeService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceDelegate;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
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
    @GetMapping("/loadBatch")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {


        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...Batch execution in progress");
        }

        return jobExecution.getStatus();
    }

}
