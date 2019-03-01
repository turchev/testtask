package com.haulmont.testtask.view;

import java.util.List;

import com.haulmont.testtask.entity.OrdersWithFio;

@SuppressWarnings("serial")
public class OrdersWindowEdit extends OrdersWindowAbstract{


	protected OrdersWindowEdit(List<OrdersWithFio> orders, Long selectedOrders) {
		super(orders);
		super.setCaption("Редактировать заявку");
		
	}

	@Override
	protected void btnCancelClick() {
		close();		
	}

	@Override
	protected void btnAppleClick() {
		// TODO Auto-generated method stub
		
	}
}
