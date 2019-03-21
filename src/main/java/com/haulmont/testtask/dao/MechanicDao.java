package com.haulmont.testtask.dao;

import com.haulmont.testtask.entity.Mechanic;
import com.haulmont.testtask.entity.Mechanic.Stat;

public interface MechanicDao extends IDao<Mechanic> {
	Stat getStat(Long id) throws DaoException;
}
