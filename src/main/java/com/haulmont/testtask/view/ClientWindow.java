package com.haulmont.testtask.view;

import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientWindow extends ClientWindowAbstract {

	@Override
	protected void btnAppleClick() {
		Notification.show("TODO", "Применить", Notification.Type.HUMANIZED_MESSAGE);
		// TODO Auto-generated method stub		
	}
}
