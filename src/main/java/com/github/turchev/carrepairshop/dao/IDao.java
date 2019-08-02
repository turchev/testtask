package com.github.turchev.carrepairshop.dao;

import java.util.List;

import com.github.turchev.carrepairshop.domain.AbstractEntity;

public interface IDao<T extends AbstractEntity> {

	T findById(long id) throws DaoException;

	List<T> findAll() throws DaoException;

	void update(T dataSet) throws DaoException;
	
	void create(T dataSet) throws DaoException;
	
	void delete(long id) throws DaoException;
}
