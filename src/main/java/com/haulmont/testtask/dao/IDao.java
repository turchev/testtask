package com.haulmont.testtask.dao;

import java.util.List;
import com.haulmont.testtask.entity.AEntity;

public interface IDao<T extends AEntity> {
    
    T getById(long id) throws DaoException;    
    List<T> findAll() throws DaoException;    
    void save(T dataSet) throws DaoException;    
    void shutdown() throws DaoException;    
}
