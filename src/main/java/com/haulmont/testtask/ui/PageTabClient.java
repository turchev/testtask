package com.haulmont.testtask.ui;

import java.util.List;

import com.haulmont.testtask.connection.DsType;
import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.entity.Client;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;

public class PageTabClient extends PageTabAbstract {
	DaoFactory hsqlDaoFactory;
	ClientDao clientDao;	

	public PageTabClient() throws DaoException {
		Grid<Client> grid2 = new Grid<Client>(Client.class);
//		grid = new Grid<>(Client.class);
		grid2.setWidth(100.0f, Unit.PERCENTAGE);
		hsqlDaoFactory = DaoFactory.get(DsType.HSQLDB);
		clientDao = hsqlDaoFactory.getClientDAO();
//		for (Client client : clientDao.findAll()) {
//	        grid2.(client.getId(), client.getFirstName(), client.getLastName(), client.getPatronnymic());
//	    }
		List<Client> clients = clientDao.findAll();
		grid2.setItems(clients);
		webPage.addComponent(grid2);
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
