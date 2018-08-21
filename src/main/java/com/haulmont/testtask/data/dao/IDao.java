package com.haulmont.testtask.data.dao;

import java.util.List;

import com.haulmont.testtask.data.DataException;
import com.haulmont.testtask.data.dataSets.AbstractDataSet;

public interface IDao<T extends AbstractDataSet> {
	
	T read(long id) throws DataException;
	List<T> readAll() throws DataException;
    void save(T dataSet) throws DataException;  
    void shutdown() throws DataException;
    
}
