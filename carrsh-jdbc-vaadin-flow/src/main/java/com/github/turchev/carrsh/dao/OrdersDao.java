package com.github.turchev.carrsh.dao;

import java.util.List;

import com.github.turchev.carrsh.domain.orders.OrdersWithFio;

public interface OrdersDao extends IDao<OrdersWithFio> {	

	List<OrdersWithFio> findUsingFilter(String findDescription, String status, String clientFio) throws DaoException;	
	
}
