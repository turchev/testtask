package com.haulmont.testtask.domain.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends APerson {

	public Client() {
	}

	public Client(String lastName, String firstName, String patronnymic) {
		super.lastName = lastName;
		super.firstName = firstName;
		super.patronnymic = patronnymic;
	}

	@Column(name = "phone")
	private String phone = "";

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
