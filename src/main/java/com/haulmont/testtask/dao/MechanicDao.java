package com.haulmont.carrepairshop.dao;

import java.util.List;

import com.haulmont.carrepairshop.domain.ShortName;
import com.haulmont.carrepairshop.domain.person.Mechanic;

public interface MechanicDao extends IDao<Mechanic> {

	List<Mechanic.Stat> getStatAll() throws DaoException;

	List<ShortName<Mechanic>> findAllShortName() throws DaoException;

	ShortName<Mechanic> getFioById(Long id) throws DaoException;
}
