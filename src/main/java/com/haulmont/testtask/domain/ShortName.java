package com.haulmont.testtask.domain;

import com.haulmont.testtask.domain.person.AbstractPerson;

public class ShortName<T extends AbstractPerson> {
	private Long id;
	private String fio;

	public ShortName() {
	}

	public ShortName(T person) {
		id = person.getId();
		fio = person.getLastName() + " " + (person.getFirstName()).charAt(0) + ". "
				+ (person.getPatronnymic()).charAt(0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	@Override
	public String toString() {
		return fio;
	}
}
