package com.haulmont.testtask.data.dao.impl;

import java.util.List;
import com.haulmont.testtask.data.dao.MechanicDao;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.Mechanic;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("daff73af-3f2a-4a80-8798-5c2ef20bb727")
public class MechanicDaoImpl implements MechanicDao {
    @objid ("eced59eb-82c8-465b-a01a-c4200b44d3de")
    @Override
    public Mechanic read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("239e0573-a387-4b6f-a130-046c38913775")
    @Override
    public List<Mechanic> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("d9d033cb-1dca-4b05-8306-5d31ac78c7bf")
    @Override
    public void save(Mechanic dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("b1a8ee8c-98cc-44c5-b9b7-b1b4809970bf")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
