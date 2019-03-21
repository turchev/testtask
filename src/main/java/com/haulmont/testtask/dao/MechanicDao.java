package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.entity.Mechanic;

public interface MechanicDao extends IDao<Mechanic> {
	List<Mechanic.Stat> getStat(Long id) throws DaoException;
}
