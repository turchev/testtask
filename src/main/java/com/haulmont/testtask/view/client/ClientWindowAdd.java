package com.haulmont.testtask.view.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.domain.person.Client;
import com.haulmont.testtask.view.UiException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class ClientWindowAdd extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();


	protected ClientWindowAdd() throws UiException {
		super.setCaption("Создать запись о клиенте");
		LOG.debug("Created ClientWindowAdd");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		try {
			Client client = new Client(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());
			client.setPhone(txtPhone.getValue());
			clientDao.create(client);
			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
