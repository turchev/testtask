package com.haulmont.testtask.dao.impl;

import java.util.List;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.entity.Mechanic;



public class MechanicDaoHSQLDB implements MechanicDao {
    
    @Override
    public Mechanic findById(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public List<Mechanic> findAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public void save(Mechanic dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
