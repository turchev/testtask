package com.haulmont.testtask.data.dao;

import java.util.List;
import com.haulmont.testtask.data.dao.exeption.DaoException;
import com.haulmont.testtask.data.entity.AbstractEntity;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("79d100fe-c52f-4417-ac49-88bc88fda119")
public interface AbstractDao<T extends AbstractEntity> {
    @objid ("5a0b4067-3509-4b4e-ada3-9f529963bde5")
    T read(long id) throws DaoException;

    @objid ("be0101a7-42ae-4963-b041-708792b6bc8b")
    List<T> readAll() throws DaoException;

    @objid ("30ba1a3d-07b3-459b-90c4-b68dbca00c61")
    void save(T dataSet) throws DaoException;

    @objid ("169ce19f-42d5-4e92-97f2-7812edb977b4")
    void shutdown() throws DaoException;

}
