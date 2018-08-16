package com.haulmont.testtask.data;

import java.io.Serializable;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mechanic")
@SuppressWarnings("serial")
public class MechanicDataSet implements Serializable, Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName = "";

	@Column(name = "last_name")
	private String lastName = "";

	@Column(name = "patronnymic")
	private String patronnymic = "";

	@Column(name = "wages")	
	private Currency wages = Currency.getInstance("RUB");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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