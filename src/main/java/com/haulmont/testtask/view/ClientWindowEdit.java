package com.haulmont.testtask.view;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientWindowEdit extends ClientWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private Long id;

	public ClientWindowEdit(Long id) {
		super.setCaption("Редактировать данные клиента");
		this.id = id;		 
		try {
			Client client = clientDao.findById(id);
			super.txtFirstName.setValue(client.getFirstName());
			super.txtLastName.setValue(client.getLastName());
			super.txtPatronnymic.setValue(client.getPatronnymic());
			super.txtPhone.setValue(client.getPhone());
			LOG.debug("Created ClientWindowEdit");
//			throw new UiException("Лови затрещину!!!");			
		} catch (Exception e) {
			LOG.error(e);
			return;
		}		
	}	
	
	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		Client client = new Client(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());	
		client.setPhone(txtPhone.getValue());
		client.setId(id);
		try {
			clientDao.update(client);
			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
			close();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
