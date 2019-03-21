package com.haulmont.testtask.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mechanic")
public class Mechanic extends AbstractEntity {

	@Column(name = "last_name")
	private String lastName = "";

	@Column(name = "first_name")
	private String firstName = "";

	@Column(name = "patronnymic")
	private String patronnymic = "";

	@Column(name = "wages")
	private BigDecimal wages;

	public Mechanic(Long id) {
		setId(id);
	}

	public Mechanic(String lastName, String firstName, String patronnymic) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronnymic = patronnymic;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronnymic() {
		return patronnymic;
	}

	public void setPatronnymic(String patronnymic) {
		this.patronnymic = patronnymic;
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

	public class Stat {

		private long id;
		private String mechanicFio;
		private Integer orders_sum;
		private Integer hh_sum;
		private BigDecimal price_sum;

		public Stat() {
			this.id = Mechanic.this.getId();
		}

		public Stat(String mechanicFio, Integer orders_sum, Integer hh_sum, BigDecimal price_sum) {
			this.id = Mechanic.this.getId();
			this.mechanicFio = mechanicFio;
			this.orders_sum = orders_sum;
			this.hh_sum = hh_sum;
			this.price_sum = price_sum;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getMechanicFio() {
			return mechanicFio;
		}

		public void setMechanicFio(String mechanicFio) {
			this.mechanicFio = mechanicFio;
		}

		public Integer getOrders_sum() {
			return orders_sum;
		}

		public void setOrders_sum(Integer orders_sum) {
			this.orders_sum = orders_sum;
		}

		public Integer getHh_sum() {
			return hh_sum;
		}

		public void setHh_sum(Integer hh_sum) {
			this.hh_sum = hh_sum;
		}

		public BigDecimal getPrice_sum() {
			return price_sum;
		}

		public void setPrice_sum(BigDecimal price_sum) {
			this.price_sum = price_sum;
		}
	}
}
