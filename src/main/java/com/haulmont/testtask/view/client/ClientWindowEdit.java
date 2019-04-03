package com.haulmont.testtask.view.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.domain.person.Client;
import com.haulmont.testtask.view.UiException;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class ClientWindowEdit extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private Long id;

	protected ClientWindowEdit(Long id) throws UiException {
		try {
			super.setCaption("Редактировать данные клиента");
			this.id = id;
			Client client = clientDao.findById(id);
			super.txtFirstName.setValue(client.getFirstName());
			super.txtLastName.setValue(client.getLastName());
			super.txtPatronnymic.setValue(client.getPatronnymic());
			super.txtPhone.setValue(client.getPhone());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created ClientWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		try {
			Client client = new Client();
			super.binder.writeBean(client);			
			client.setId(id);
			super.clientDao.update(client);
			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
			close();
		} catch (ValidationException ev) {
			LOG.debug(ev);
			Notification.show("Проверьте корректность заполнения полей данных");			
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
