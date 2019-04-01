package com.haulmont.testtask.domain.person;

import javax.persistence.Column;

import com.haulmont.testtask.domain.AEntity;

public abstract class APerson extends AEntity {
	
	@Column(name = "last_name")
	protected String lastName = "";

	@Column(name = "first_name")
	protected String firstName = "";

	@Column(name = "patronnymic")
	protected String patronnymic = "";

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
}
