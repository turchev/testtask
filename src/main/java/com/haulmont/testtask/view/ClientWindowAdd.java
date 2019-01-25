package com.haulmont.testtask.view;

import com.haulmont.testtask.dao.ClientDao;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientWindowAdd extends ClientWindowAbstract {

	public ClientWindowAdd()  {
		super.setCaption("Создать запись о клиенте");		
	}	

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick(ClientDao clientDao) {
		Notification.show("TODO", "Применить", Notification.Type.HUMANIZED_MESSAGE);
		// TODO Auto-generated method stub
	}
}
