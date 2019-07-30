package com.github.turchev.carrsh.domain.orders;

public class OrdersWithFio extends Orders {
	private String clientFio;
	private String mechanicFio;

	public OrdersWithFio() {
	}

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
