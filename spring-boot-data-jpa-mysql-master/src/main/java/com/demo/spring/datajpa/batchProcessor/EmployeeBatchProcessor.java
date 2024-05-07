package com.demo.spring.datajpa.batchProcessor;

import com.demo.spring.datajpa.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class EmployeeBatchProcessor implements ItemProcessor<Employee, Employee> {

    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public EmployeeBatchProcessor() {
        DEPT_NAMES.put("1010", "ATCI");
        DEPT_NAMES.put("1011", "Payroll");
        DEPT_NAMES.put("1012", "Infra");
    }

    @Override
    public Employee process(Employee employee) throws Exception {
        String deptCode = employee.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        employee.setDept(dept);
        employee.setTime(new Date());
        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return employee;
    }


}
