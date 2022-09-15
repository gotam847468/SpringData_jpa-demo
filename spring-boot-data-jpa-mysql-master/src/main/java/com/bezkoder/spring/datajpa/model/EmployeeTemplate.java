package com.bezkoder.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeTemplate implements Serializable {
    private static final long SerialVersionUID = 10l;
    @Id
    private Long id;

    @Column(name = "ename")
    private String ename;

    @Column(name = "deptName")
    private String deptName;

    @Column(name = "deptId")
    private String deptId;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private String salary;

}
