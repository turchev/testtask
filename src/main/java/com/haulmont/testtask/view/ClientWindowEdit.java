package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.ClientDao;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientWindowEdit extends ClientWindowAbstract {

	public ClientWindowEdit(Long id) {
		super.setCaption("Редактировать данные клиента");
	}
	
	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick(ClientDao clientDao) {
		Notification.show("TODO", "Редактировать", Notification.Type.HUMANIZED_MESSAGE);
		// TODO Auto-generated method stub
	}
}
