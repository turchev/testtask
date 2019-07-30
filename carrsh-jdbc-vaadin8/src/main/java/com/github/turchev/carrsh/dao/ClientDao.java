package com.github.turchev.carrsh.dao;

import java.util.List;

import com.github.turchev.carrsh.domain.ShortName;
import com.github.turchev.carrsh.domain.person.Client;

public interface ClientDao extends IDao<Client> {

	List<String> getLastNameList() throws DaoException;

	List<ShortName<Client>> findAllShortName() throws DaoException;

	ShortName<Client> getFioById(Long id) throws DaoException;

}
