package com.haulmont.testtask.ui;

import java.util.List;
import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;

public class PageTabClient extends PageTabFactory {
	DaoFactory hsqlDaoFactory;
	ClientDao clientDao;	

	public PageTabClient() throws UiException{
		try {
			Grid<Client> grid = new Grid<Client>(Client.class);
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			clientDao = hsqlDaoFactory.getClientDAO();
			List<Client> clients = clientDao.findAll();
			grid.setItems(clients);
			webPage.addComponent(grid);
		} catch (Exception e) {
			throw new UiException(e);
		}		
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
