package com.example.demoauth.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "employeeSchedule")
public class EmployeeSchedule implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int workDays;
	private int dayOff;
	private int sickLeave;
	private int vacationTaken;
	private int vacationForTheYear;
	private int vacationLeft;
	private String motherhood;	
	
	public EmployeeSchedule() {
		
		workDays = 5;
		dayOff = 2;
		sickLeave = 0;
		vacationTaken = 0;
		vacationForTheYear = 21;
		vacationLeft = 21;
		motherhood = "no";
		

	}

	public EmployeeSchedule(Long id, int workDays, int dayOff, int sickLeave, int vacationTaken,
			int vacationForTheYear, int vacationLeft, String motherhood) {
		this.id = id;
		this.workDays = workDays;
		this.dayOff = dayOff;
		this.sickLeave = sickLeave;
		this.vacationTaken = vacationTaken;
		this.vacationForTheYear = vacationForTheYear;
		this.vacationLeft = vacationLeft;
		this.motherhood = motherhood;
		
	}

	

	public Long getId() {
		return id;
	}

	public void setSheduleId(Long id) {
		this.id = id;
	}

	public int getWorkDays() {
		return workDays;
	}

	public void setWorkDays(int workDays) {
		this.workDays = workDays;
	}

	public int getDayOff() {
		return dayOff;
	}

	public void setDayOff(int dayOff) {
		this.dayOff = dayOff;
	}

	public int getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(int sickLeave) {
		this.sickLeave = sickLeave;
	}

	public int getVacationTaken() {
		return vacationTaken;
	}

	public void setVacationTaken(int vacationTaken) {
		this.vacationTaken = vacationTaken;
	}

	public int getVacationForTheYear() {
		return vacationForTheYear;
	}

	public void setVacationForTheYear(int vacationForTheYear) {
		this.vacationForTheYear = vacationForTheYear;
	}

	public int getVacationLeft() {
		return vacationLeft;
	}

	public void setVacationLeft(int vacationLeft) {
		this.vacationLeft = vacationLeft;
	}

	public String getMotherhood() {
		return motherhood;
	}

	public void setMotherhood(String motherhood) {
		this.motherhood = motherhood;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
