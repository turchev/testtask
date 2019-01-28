package com.haulmont.testtask.view;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Client;
import com.vaadin.ui.Notification;

@SuppressWarnings("serial")
public class ClientWindowEdit extends ClientWindowAbstract {
	private Long id;

	public ClientWindowEdit(Long id) {
		super.setCaption("Редактировать данные клиента");
		this.id = id;
		Client client;
		try {
			client = clientDao.findById(this.id);
			super.txtFirstName.setValue(client.getFirstName());
			super.txtLastName.setValue(client.getLastName());
			super.txtPatronnymic.setValue(client.getPatronnymic());
			super.ptxtPhone.setValue(client.getPhone());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		Notification.show("TODO", "Редактировать", Notification.Type.HUMANIZED_MESSAGE);		
	}
}
