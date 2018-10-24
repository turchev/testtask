package com.haulmont.testtask.data.dao.impl;

import java.util.List;
import com.haulmont.testtask.data.dao.MechanicDao;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.Mechanic;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("60c08b66-78de-4089-9525-921b253964d2")
public class MechanicDaoImpl implements MechanicDao {
    @objid ("683927af-2880-447a-9cc1-75488c7f8423")
    @Override
    public Mechanic read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("9bd60e2e-2589-40e4-b950-6456b25e49e4")
    @Override
    public List<Mechanic> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("85a13f98-370c-4c93-aadd-b5436bf36d4d")
    @Override
    public void save(Mechanic dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("e950f289-30af-4a62-a70a-96bd07a052ea")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
