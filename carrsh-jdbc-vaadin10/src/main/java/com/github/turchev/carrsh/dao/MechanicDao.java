package com.github.turchev.carrsh.dao;

import java.util.List;

import com.github.turchev.carrsh.domain.ShortName;
import com.github.turchev.carrsh.domain.person.Mechanic;

public interface MechanicDao extends IDao<Mechanic> {

	List<Mechanic.Stat> getStatAll() throws DaoException;

	List<ShortName<Mechanic>> findAllShortName() throws DaoException;

	ShortName<Mechanic> getFioById(Long id) throws DaoException;
}
