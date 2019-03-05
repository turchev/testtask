package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientWindowAdd extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private ClientDao clientDao;

	public ClientWindowAdd() throws UiException {
		super.setCaption("Создать запись о клиенте");
		try {
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDAO();
		} catch (Exception e) {
			throw new UiException(e);
		}
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
