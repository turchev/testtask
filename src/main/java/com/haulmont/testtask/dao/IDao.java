package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.data.AbstractEntity;



public interface IDao<T extends AbstractEntity> {
    
    T read(long id) throws DaoException;

    
    List<T> readAll() throws DaoException;

    
    void save(T dataSet) throws DaoException;

    
    void shutdown() throws DaoException;

}
