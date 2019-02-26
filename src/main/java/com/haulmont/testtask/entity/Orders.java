package com.haulmont.testtask.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity {

	@Column(name = "description", length = 5000)
	private String description = "";

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Long clientId;

	@ManyToOne
	@JoinColumn(name = "mechanic_id")
	private Long mechanicId;

	@Column(name = "status")
	private OrderStatusType status;

	@Column(name = "date_creat")
	private LocalDateTime dateCreat;

	@Column(name = "completion_date")
	private LocalDateTime completionDate;

	@Column(name = "price")
	private BigDecimal price;

	public Orders() {
	}

	public Orders(String description, Long clientId, Long mechanicId) {
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreat() {
		return dateCreat;
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

	public void setDateCreat(LocalDateTime dateCreat) {
		this.dateCreat = dateCreat;
	}

	public LocalDateTime getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDateTime completionDate) {
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
