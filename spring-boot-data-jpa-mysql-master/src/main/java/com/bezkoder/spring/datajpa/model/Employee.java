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
@Table(name = "employee")
public class Employee implements Serializable {
	private static final long SerialVersionUID=10l;
	@Id
	private int id;

	@Column(name = "ename")
	private String ename;

	@Column(name = "dept")
	private String dept;

	@Column(name = "email")
	private String email;

	@Column(name = "salary")
	private double salary;

	@Column(name = "age")
	private int age;

	@Column(name = "time")
	private Date time;
}
