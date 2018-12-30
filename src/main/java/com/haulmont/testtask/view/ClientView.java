package com.haulmont.testtask.view;

import java.util.List;
import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientView extends AbstractView implements View {
	private DaoFactory hsqlDaoFactory;
	private ClientDao clientDao;

	public ClientView() throws DaoException, UiException {
		hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
		clientDao = hsqlDaoFactory.getClientDAO();
		refresh();
	}

	public void refresh() throws UiException {
		try {
			Grid<Client> grid = new Grid<Client>(Client.class);
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			List<Client> clients = clientDao.findAll();
			grid.setItems(clients);
			grid.setColumnOrder("id", "firstName", "lastName", "patronnymic", "phone");
			this.addComponent(grid);
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

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to Clients View");
	}
}
