package com.haulmont.testtask.view;

import java.util.List;
import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class OrdersWindowAdd extends OrdersWindowAbstract {

	public OrdersWindowAdd(List<OrdersWithFio> orders) {
		super(orders);
		super.setCaption("Создать заявку");
		ntsStatus.setVisible(false);
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {

		if (cmbClient.getValue() == null) {
			Notification.show("Выберите клиента из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}

		if (cmbMechanic.getValue() == null) {
			Notification.show("Выберите механика из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}

		Notification.show("Клиент: " + cmbClient.getValue().getClientFio() + " " + cmbClient.getValue().getClientId()
				+ "\n " + "Механик: " + cmbMechanic.getValue().getMechanicFio() + " "
				+ cmbMechanic.getValue().getMechanicId());

	}
}
