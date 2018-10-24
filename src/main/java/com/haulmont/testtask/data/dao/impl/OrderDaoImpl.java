package com.haulmont.testtask.data.dao.impl;

import java.util.List;
import com.haulmont.testtask.data.dao.OrderDao;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.Order;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("903ec2ce-fc72-49d1-a5d1-c1c3b7a51740")
public class OrderDaoImpl implements OrderDao {
    @objid ("c79afea8-1266-419c-9d69-359059278664")
    @Override
    public Order read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("c51504e3-059a-4cfd-bd15-d34098d0f26f")
    @Override
    public List<Order> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("d10b8bf3-39b9-47c6-be93-444aa15d05af")
    @Override
    public void save(Order dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("8bfd236c-b148-4add-ba05-10dc11dd26c4")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
