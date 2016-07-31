package com.example.day6assignmentsql;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable{
	
	int empid;
	String name;
	Double salary;
	String joiningdate;
	int dept_id;
	public Employee(){}
	
	public Employee(int empid, String name, double salary, String joiningdate,
			int dept_id) {
		super();
		this.empid = empid;
		this.name = name;
		this.salary = salary;
		this.joiningdate = joiningdate;
		this.dept_id = dept_id;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public int getDept_id() {
		return dept_id;
	}

	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", salary="
				+ salary + ", joiningdate=" + joiningdate + ", dept_id="
				+ dept_id + "]";
	}
	
	
}
