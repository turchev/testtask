package com.haulmont.testtask.data.dao.impl;

import java.util.List;
import com.haulmont.testtask.data.dao.ClientDao;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.Client;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("fd159f53-7aba-4203-835a-26bac3a3abfa")
public class ClientDaoImpl implements ClientDao {
    @objid ("f4d4e966-36e1-40d3-a79d-3a34f412475a")
    @Override
    public Client read(long id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("2f515f5a-742f-47d5-b8d6-64c4b52328e2")
    @Override
    public List<Client> readAll() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @objid ("f4b1fc0c-73ef-47a0-a3e2-bf7f3d5207df")
    @Override
    public void save(Client dataSet) throws DaoException {
        // TODO Auto-generated method stub
    }

    @objid ("43d52b8d-cfbf-4b58-87ce-7cdbb532b8e9")
    @Override
    public void shutdown() throws DaoException {
        // TODO Auto-generated method stub
    }

}
