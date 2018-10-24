package com.haulmont.testtask.data.dao;

import java.util.List;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.AbstractEntity;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("533d2b59-9149-430e-9eeb-910d76fb6942")
public interface AbstractDao<T extends AbstractEntity> {
    @objid ("f68244b5-d3a9-4388-8ddf-e6477d6d1931")
    T read(long id) throws DaoException;

    @objid ("c8151aec-882d-4be7-8e99-74fba954066d")
    List<T> readAll() throws DaoException;

    @objid ("8daf8f58-ca9b-4457-8a77-2994e9bd7c1a")
    void save(T dataSet) throws DaoException;

    @objid ("e3142f9a-dd61-403c-8b91-3ad8f614fed8")
    void shutdown() throws DaoException;

}
