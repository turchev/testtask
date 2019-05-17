package com.haulmont.carrepairshop.dao;

import java.util.List;

import com.haulmont.carrepairshop.domain.orders.OrdersWithFio;

public interface OrdersDao extends IDao<OrdersWithFio> {	

	List<OrdersWithFio> findUsingFilter(String findDescription, String status, String clientFio) throws DaoException;	
	
}
