package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.entity.OrdersWithFio;

public interface OrdersDao extends IDao<OrdersWithFio> {	

	List<OrdersWithFio> findUsingFilter(String description, String status, Long clientId) throws DaoException;
	
}
