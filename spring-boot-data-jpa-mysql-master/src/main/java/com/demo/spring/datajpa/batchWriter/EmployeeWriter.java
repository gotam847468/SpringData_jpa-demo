package com.demo.spring.datajpa.batchWriter;

import com.demo.spring.datajpa.model.Employee;
import com.demo.spring.datajpa.repository.EmployeeRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeWriter implements ItemWriter<Employee> {
    private EmployeeRepository EmployeeRepository;

    @Autowired
    public EmployeeWriter(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public void write(List<? extends Employee> Employees) throws Exception {
        System.out.println("Data Saved for Employees: " + Employees);
        EmployeeRepository.saveAll(Employees);
    }
}
