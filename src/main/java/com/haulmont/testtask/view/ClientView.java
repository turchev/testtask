package com.haulmont.testtask.view;

import java.util.List;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientView extends AbstractView implements View {
	DaoFactory hsqlDaoFactory;
	ClientDao clientDao;	

	public ClientView() throws UiException{
		try {
			Grid<Client> grid = new Grid<Client>(Client.class);
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			clientDao = hsqlDaoFactory.getClientDAO();
			List<Client> clients = clientDao.findAll();
			grid.setItems(clients);
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
