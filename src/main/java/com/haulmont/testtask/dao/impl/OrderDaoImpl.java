package com.haulmont.testtask.dao.impl;

import java.util.List;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.OrderDao;
import com.haulmont.testtask.data.Order;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("0f9ef8c1-fb6b-4ebd-b58e-8482432226a4")
public class OrderDaoImpl implements OrderDao {
    @objid ("25173ce6-58a5-4062-aed0-9644a1e56c09")
    @Override
    public Order read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("90404d0e-5353-44d4-9599-32b7d8b6d580")
    @Override
    public List<Order> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("651b9395-b258-4086-8d68-9eb21ee40fe7")
    @Override
    public void save(Order dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("eea39baa-d835-4205-91da-4de59b03f81c")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
