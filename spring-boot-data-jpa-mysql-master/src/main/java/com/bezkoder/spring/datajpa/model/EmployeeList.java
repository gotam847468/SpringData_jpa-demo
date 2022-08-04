package com.bezkoder.spring.datajpa.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeList implements Serializable {
    private static final long SerialVersionUID=10l;
        private List<Employee> employees;
}
