package com.haulmont.testtask.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders extends AEntity {

	@Column(name = "description", length = 5000)
	private String description = "";

	@ManyToOne
	@JoinColumn(name = "client_id")	
	private Client client;	

	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Mechanic mechanic;

	@Column(name = "status")
	private OrderStatusType status;

	@Column(name = "date_creat")
	private Timestamp dateCreat;

	@Column(name = "completion_date")
	private Timestamp completionDate;

	@Column(name = "price")
	private BigDecimal price;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Timestamp getDateCreat() {
		return dateCreat;
	}

	public void setDateCreat(Timestamp dateCreat) {
		this.dateCreat = dateCreat;
	}

	public Timestamp getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Timestamp completionDate) {
		this.completionDate = completionDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public OrderStatusType getStatus() {
		return status;
	}

	public void setStatus(OrderStatusType status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return description + " " + status;
	}
}
