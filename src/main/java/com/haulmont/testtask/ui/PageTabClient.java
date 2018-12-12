package com.haulmont.testtask.ui;

import java.util.Collection;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.DaoFactory.TypeDb;
import com.haulmont.testtask.entity.Client;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;

public class PageTabClient extends PageTabAbstract {
	DaoFactory hsqlDaoFactory;
	ClientDao clientDao;

	public PageTabClient() throws DaoException {
		grid = new Grid<>(Client.class);
		grid.setWidth(100.0f, Unit.PERCENTAGE);
		hsqlDaoFactory = DaoFactory.get(TypeDb.HSQLDB);
		clientDao = hsqlDaoFactory.getClientDAO();
		grid.setItems(clientDao.findAll());
		webPage.addComponent(grid);
	}

	@Override
	void btnAddClick() {
		// TODO Auto-generated method stub

	}

	@Override
	void btnChangeClick() {
		// TODO Auto-generated method stub

	}

	@Override
	void btnDeleteClick() {
		// TODO Auto-generated method stub

	}

}
