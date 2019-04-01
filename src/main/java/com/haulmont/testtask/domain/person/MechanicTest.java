package com.haulmont.testtask.domain.person;

import java.math.BigDecimal;

public class MechanicTest{		
	private long id;
	private String lastName;
	private String firstName;
	private String patronnymic;	
	private Integer TxtInteger;	
	private BigDecimal wages;
	
	public MechanicTest() {
	}	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronnymic() {
		return patronnymic;
	}

	public void setPatronnymic(String patronnymic) {
		this.patronnymic = patronnymic;
	}
	
	public Integer getTxtInteger() {
		return TxtInteger;
	}

	public void setTxtInteger(Integer txtInteger) {
		TxtInteger = txtInteger;
	}

	public BigDecimal getWages() {
		return wages;
	}

	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName + " " + patronnymic;
	}
}
