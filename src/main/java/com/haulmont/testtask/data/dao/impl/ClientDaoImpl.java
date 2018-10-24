package com.haulmont.testtask.data.dao.impl;

import java.util.List;
import com.haulmont.testtask.data.dao.ClientDao;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.Client;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c979a973-eac6-4cee-b0ec-fb719526b401")
public class ClientDaoImpl implements ClientDao {
    @objid ("5d39df1e-a0e7-41a0-aeef-76bcfd115716")
    @Override
    public Client read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("5bd174fe-d378-4d9f-9dd0-af3ebcbcf7b1")
    @Override
    public List<Client> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("98a0d411-2e4d-41f6-a3a2-939a0a59a7a2")
    @Override
    public void save(Client dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("b495f346-7da2-4c94-a0fb-8f11c235c1f0")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
