package com.haulmont.testtask.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders_with_fio")
public class OrdersWithFio extends Orders {

	@Column(name = "client_fio")	
	private String clientFio;	

	@Column(name = "mechanic_fio")
	private String mechanicFio;
	
	public String getClientFio() {
		return clientFio;
	}

	public void setClientFio(String clientFio) {
		this.clientFio = clientFio;
	}

	public String getMechanicFio() {
		return mechanicFio;
	}

	public void setMechanicFio(String mechanicFio) {
		this.mechanicFio = mechanicFio;
	}	
}
