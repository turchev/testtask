package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.entity.Mechanic.Stat;

public interface MechanicDao extends IDao<Mechanic> {
	Stat getStat(Long id) throws DaoException;
	List<Stat> getStatAll() throws DaoException;
}
