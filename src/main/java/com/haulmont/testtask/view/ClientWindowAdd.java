package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientWindowAdd extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private ClientDao clientDao;

	public ClientWindowAdd() throws UiException {
		try {
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDAO();
			super.setCaption("Создать запись о клиенте");
			LOG.debug("Created ClientWindowAdd");
//			throw new UiException("Лови затрещину от ClientWindowAdd!!!");	
		} catch (Exception e) {					
			throw new UiException(e);			
		}				
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		Client client = new Client(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());
		client.setPhone(txtPhone.getValue());
		try {
			clientDao.create(client);
		} catch (DaoException e) {
			LOG.error(e);
		}
		UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
		close();
	}
}
