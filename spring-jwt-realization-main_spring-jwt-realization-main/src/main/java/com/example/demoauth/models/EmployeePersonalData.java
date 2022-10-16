package com.example.demoauth.models;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employeePersonalData")
@Access(value = AccessType.FIELD)
public class EmployeePersonalData implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;
	private String name;
	private String surname;
	private String startWorkDay;
	private int experience;
	private String salary;
	private String email;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "assignment_id", referencedColumnName = "id")
	Assignment assignment;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private EmployeeSchedule schedule;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	public EmployeePersonalData() {
		name = "Not defined";
		surname = "Not defined";
		startWorkDay = "01.01.2022";
		experience = 4;
		salary = "3000";
		email = "test@gmail.com";

	}

	public EmployeePersonalData(String name, String surname, String startWorkDay, int experience, String salary,
			String email, Assignment assignment, EmployeeSchedule schedule, Order order) {
		this.name = name;
		this.surname = surname;
		this.startWorkDay = startWorkDay;
		this.experience = experience;
		this.salary = salary;
		this.email = email;
		this.assignment = assignment;
		this.schedule = schedule;
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStartWorkDay() {
		return startWorkDay;
	}

	public void setStartWorkDay(String startWorkDay) {
		this.startWorkDay = startWorkDay;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmployeeSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(EmployeeSchedule schedule) {
		this.schedule = schedule;
	}

	

}
