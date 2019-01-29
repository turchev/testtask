package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class ClientWindowAdd extends ClientWindowAbstract {

	public ClientWindowAdd() {
		super.setCaption("Создать запись о клиенте");
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
			UI.getCurrent().getNavigator().navigateTo("client");
			close();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
