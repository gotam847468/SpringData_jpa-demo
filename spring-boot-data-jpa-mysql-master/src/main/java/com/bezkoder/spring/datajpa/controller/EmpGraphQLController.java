package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.EmployeeTemplate;
import com.bezkoder.spring.datajpa.repository.EmpRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class EmpGraphQLController {
    private final EmpRepository empRepository;


    public EmpGraphQLController(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    @QueryMapping
    Iterable<EmployeeTemplate> employees() {
        return empRepository.findAll();
    }

    @QueryMapping
    Optional<EmployeeTemplate> employeeById(@Argument Long id) {
        return empRepository.findById(id);
    }

    @MutationMapping
    EmployeeTemplate addEmployee(@Argument EmpInput empInput) {
        EmployeeTemplate emp = new EmployeeTemplate(empInput.id(), empInput.ename(), empInput.deptName(), empInput.deptId(), empInput.email(), empInput.salary());
        return empRepository.save(emp);
    }

    @MutationMapping
    AuthoController.Status deleteEmployee(@Argument Long id) {
        try {
            empRepository.deleteById(id);
            return new AuthoController.Status("200", "Success");
        } catch (Exception e) {
            return new AuthoController.Status("200", "not able to delete");
        }
    }

    record EmpInput(Long id, String ename, String deptName, String deptId, String email, String salary) {
    }
}
