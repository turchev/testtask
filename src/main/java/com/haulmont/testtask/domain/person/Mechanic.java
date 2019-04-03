package com.haulmont.testtask.domain.person;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mechanic")
public class Mechanic extends APerson {

	@Column(name = "wages")
	private BigDecimal wages;

	public Mechanic() {
	}

	public Mechanic(Long id) {
		setId(id);
	}

	public Mechanic(String lastName, String firstName, String patronnymic) {
		super.lastName = lastName;
		super.firstName = firstName;
		super.patronnymic = patronnymic;
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
		private Integer ordersSum;
		private BigDecimal hhSum;
		private BigDecimal priceSum;

		public Stat() {
			this.id = Mechanic.this.getId();
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

		public Integer getOrdersSum() {
			return ordersSum;
		}

		public void setOrdersSum(Integer ordersSum) {
			this.ordersSum = ordersSum;
		}

		public BigDecimal getHhSum() {
			return hhSum;
		}

		public void setHhSum(BigDecimal hhSum) {
			this.hhSum = hhSum;
		}

		public BigDecimal getPriceSum() {
			return priceSum;
		}

		public void setPriceSum(BigDecimal priceSum) {
			this.priceSum = priceSum;
		}

		@Override
		public String toString() {
			return "\n" + mechanicFio + ": " + ordersSum + " заказа(ов), " + hhSum + " часов, " + priceSum + " рублей";
		}
	}
}
