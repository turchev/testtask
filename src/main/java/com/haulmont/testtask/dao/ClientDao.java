package com.haulmont.testtask.dao;

import java.util.List;

import com.haulmont.testtask.entity.Client;

public interface ClientDao extends IDao<Client> {

	List<String> getLastNameList() throws DaoException;

}
