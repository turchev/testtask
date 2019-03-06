package com.haulmont.testtask.view;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.dialogs.ConfirmDialog;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientView extends AbstractView implements View {
	private static final Logger LOG = LogManager.getLogger();
	public static final String NAME = "client";
	private DaoFactory hsqlDaoFactory;
	private ClientDao clientDao;
	private Grid<Client> grid = new Grid<>();

	public ClientView() throws UiException {
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
			showAll();
		} catch (Exception e) {
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
	protected void btnAddClick() {
		try {
			ClientWindowAdd subWindowAdd = new ClientWindowAdd();
			UI.getCurrent().addWindow(subWindowAdd);
		} catch (Exception e) {
			LOG.error(e);	
			Notification.show("Ошибка диалогового окна создания записи");
		}		
	}

	@Override
	protected void btnChangeClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите клиента из списка");
				return;
			}
			Client selectedClient = grid.asSingleSelect().getValue();
			ClientWindowEdit subWindowEdit = new ClientWindowEdit(selectedClient.getId());
			UI.getCurrent().addWindow(subWindowEdit);
		} catch (Exception e) {
			LOG.error(e);	
			Notification.show("Ошибка диалогового окна редактирования");
		}		
	}

	@Override
	protected void btnDeleteClick() {
		try {
			if (grid.asSingleSelect().isEmpty()) {
				Notification.show("Выберите клиента из списка");
				return;
			}
			Client selectedClient = grid.asSingleSelect().getValue();
			final String MESSAGE_1 = "Удаление записи " + selectedClient.getLastName() + " "
					+ selectedClient.getFirstName() + " " + selectedClient.getPatronnymic() + "?";

			ConfirmDialog.show(getUI(), "Внимание", MESSAGE_1, "Подтвердить", "Отменить", dialog -> {
				if (dialog.isConfirmed()) {
					try {
						clientDao.delete(selectedClient.getId());
						showAll();
					} catch (Exception ex) {
						LOG.error(ex);
					}
				} else {
					return;
				}
			});

		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось выполнить удаление", Type.ERROR_MESSAGE);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		LOG.debug("Welcome to Client View");
	}
}
