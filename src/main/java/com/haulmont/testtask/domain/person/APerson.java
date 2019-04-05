package com.haulmont.testtask.domain.person;

import com.haulmont.testtask.domain.AEntity;

public abstract class APerson extends AEntity {
	protected String lastName;
	protected String firstName;
	protected String patronnymic;

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
