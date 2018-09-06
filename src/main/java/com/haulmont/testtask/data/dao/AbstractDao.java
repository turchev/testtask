package com.haulmont.testtask.data.dao;

import java.util.List;

import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.AbstractEntity;

public interface AbstractDao<T extends AbstractEntity> {
	
	T read(long id) throws DaoException;
	List<T> readAll() throws DaoException;
    void save(T dataSet) throws DaoException;  
    void shutdown() throws DaoException;
    
}
