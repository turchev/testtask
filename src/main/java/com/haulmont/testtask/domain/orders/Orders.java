package com.haulmont.testtask.domain.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.haulmont.testtask.domain.AEntity;

public class Orders extends AEntity {

	private String description;
	private Long clientId;
	private Long mechanicId;
	private OrderStatusType status;
	private LocalDateTime dateCreat;
	private LocalDateTime completionDate;
	private BigDecimal price;

	public Orders() {
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
