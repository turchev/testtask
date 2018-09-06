package com.haulmont.testtask.data.entity;

import java.time.LocalDate;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.haulmont.testtask.util.OrderStatusType;

@Entity
@Table(name = "order")
public class Order extends AbstractEntity {

	@Column(name = "description")
	private String description = "";

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Long clientId;

	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Long mechanicId;

	@Column(name = "date_creat")
	private LocalDate dateCreat;

	@Column(name = "completion_date")
	private LocalDate completionDate;

	@Column(name = "price")
	private Currency price = Currency.getInstance("RUB");

	@Column(name = "status")
	private String status;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(Long mechanicId) {
		this.mechanicId = mechanicId;
	}

	public LocalDate getDateCreat() {
		return dateCreat;
	}

	public void setDateCreat(LocalDate dateCreat) {
		this.dateCreat = dateCreat;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public Currency getPrice() {
		return price;
	}

	public void setPrice(Currency price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(OrderStatusType status) {
		this.status = status.name();
	}

	@Override
	public String toString() {
		return description + " " + status;
	}

}