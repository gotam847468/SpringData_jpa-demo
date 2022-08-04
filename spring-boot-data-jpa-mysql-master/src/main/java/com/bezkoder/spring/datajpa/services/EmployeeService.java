package com.bezkoder.spring.datajpa.services;

import com.bezkoder.spring.datajpa.model.Employee;
import com.bezkoder.spring.datajpa.model.EmployeeList;
import com.bezkoder.spring.datajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RestTemplate restTemplate;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
    @Autowired
    private Environment env;

    public ResponseEntity<EmployeeList> getAllEmployees() {
        try {
            EmployeeList employeeList = new EmployeeList();
            employeeList.setEmployees(employeeRepository.findAll());
            return new ResponseEntity<>(employeeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> addEmployee(Employee employee) {
        try {
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Employee> getEmployeeById(int id) {

        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent())
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Employee>> getEmpSalary(double lb, double ub) {
        try {
            ResponseEntity<EmployeeList> responseEntityOfEmployeeList = restTemplate.exchange("http://localhost:" + env.getProperty("server.port") + "/employee/getAll", HttpMethod.GET, buildRequestEntity(), EmployeeList.class);
            List<Employee> employeeList = responseEntityOfEmployeeList.getBody().getEmployees();
            if (employeeList != null || !employeeList.isEmpty()) {
                return new ResponseEntity<>(employeeList.stream().filter(e -> (e.getSalary() >= lb && e.getSalary() <= ub)).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    static HttpEntity<Employee> buildRequestEntity() {
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        return new HttpEntity<Employee>(headers);
    }
}
