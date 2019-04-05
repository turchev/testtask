package com.haulmont.testtask.domain.person;

public class Client extends AbstractPerson {
	private String phone;

	public Client() {
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName + " " + patronnymic;
	}
}
