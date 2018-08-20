package com.haulmont.testtask.data.dataSets;

import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mechanic")
public class MechanicDataSet extends SuperDataSet {

	@Column(name = "first_name")
	private String firstName = "";

	@Column(name = "last_name")
	private String lastName = "";

	@Column(name = "patronnymic")
	private String patronnymic = "";

	@Column(name = "wages")	
	private Currency wages = Currency.getInstance("RUB");
	

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

	public Currency getWages() {
		return wages;
	}

	public void setWages(Currency wages) {		
		this.wages = wages;
	}
	
	@Override
	public String toString() {
		return lastName + " " + firstName;
	}

}