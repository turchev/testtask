package com.haulmont.testtask.view;

import java.util.List;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientView extends AbstractView implements View {
	private DaoFactory hsqlDaoFactory;
	private ClientDao clientDao;
	private Grid<Client> grid = new Grid<>();

	public ClientView() throws UiException {
		init();
		showAll();
	}

	private void init() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			clientDao = hsqlDaoFactory.getClientDAO();
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			grid.setSelectionMode(SelectionMode.SINGLE);
			grid.addColumn(Client::getId).setId("id").setCaption("№");
			grid.addColumn(Client::getLastName).setId("lastName").setCaption("Фамилия").setWidth(500);
			grid.addColumn(Client::getFirstName).setId("firstName").setCaption("Имя");
			grid.addColumn(Client::getPatronnymic).setId("patronnymic").setCaption("Отчество");
			grid.addColumn(Client::getPhone).setId("phone").setCaption("Телефон");
			grid.setColumnOrder("id", "lastName", "firstName", "patronnymic", "phone");
			this.addComponent(grid);
		} catch (Exception e) {
//			UI.getCurrent().getNavigator().navigateTo("client");
			throw new UiException(e);
		}
	}

	private void showAll() throws UiException {
		try {
			List<Client> clients = clientDao.findAll();
			grid.setItems(clients);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	@Override
	void btnAddClick() {
		ClientWindowAdd subWindowAdd = new ClientWindowAdd();
		UI.getCurrent().addWindow(subWindowAdd);
	}

	@Override
	void btnChangeClick() {
		if (grid.asSingleSelect().isEmpty()) {
			Notification.show("Выберите клиента из списка");
			return;
		}
		Client selectedClient = grid.asSingleSelect().getValue();
		ClientWindowEdit subWindowEdit = new ClientWindowEdit(selectedClient.getId());
		UI.getCurrent().addWindow(subWindowEdit);
	}

	@Override
	void btnDeleteClick() {
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
