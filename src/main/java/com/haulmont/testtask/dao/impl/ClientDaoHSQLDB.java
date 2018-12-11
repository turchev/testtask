package com.haulmont.testtask.dao.impl;

import java.util.List;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Client;


public class ClientDaoHSQLDB implements ClientDao {
    
    @Override
    public Client getById(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public List<Client> findAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public void save(Client dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
