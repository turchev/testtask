package com.haulmont.testtask.dao.impl;

import java.util.List;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.data.Orders;



public class OrdersDaoHSQLDB implements OrdersDao {
    
    @Override
    public Orders read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public List<Orders> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public void save(Orders dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
