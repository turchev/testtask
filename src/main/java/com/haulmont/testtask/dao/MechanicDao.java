package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.domain.ShortName;
import com.haulmont.testtask.domain.person.Mechanic;

public interface MechanicDao extends IDao<Mechanic> {

	List<Mechanic.Stat> getStatAll() throws DaoException;

	List<ShortName<Mechanic>> findAllShortName() throws DaoException;

	ShortName<Mechanic> getFioById(Long id) throws DaoException;
}
