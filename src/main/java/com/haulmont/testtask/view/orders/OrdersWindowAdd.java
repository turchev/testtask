package com.haulmont.testtask.view.orders;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.domain.orders.OrderStatusType;
import com.haulmont.testtask.domain.orders.OrdersWithFio;
import com.haulmont.testtask.view.UiException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class OrdersWindowAdd extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();


	protected OrdersWindowAdd() throws UiException {
		try {
			super.setCaption("Создать заявку");
			super.ntsStatus.setValue(OrderStatusType.Принят);
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created OrdersWindowAdd");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {

		if (cmbClient.isEmpty()) {
			Notification.show("Выберите клиента из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		if (cmbMechanic.isEmpty()) {
			Notification.show("Выберите механика из списка или создайте новую запись", Type.WARNING_MESSAGE);
			return;
		}
		if (ntsStatus.isEmpty()) {
			Notification.show("Задайте статус заявки", Type.WARNING_MESSAGE);
			return;
		}
		if (txrDescription.isEmpty()) {
			Notification.show("Описание заявки не может быть пустым", Type.WARNING_MESSAGE);
			return;
		}
		if (dtfDateCreat.isEmpty()) {
			Notification.show("Укажите дату заявки", Type.WARNING_MESSAGE);
			return;
		}

		try {
			OrdersWithFio order = new OrdersWithFio(txrDescription.getValue(), cmbClient.getValue().getId(),
					cmbMechanic.getValue().getId());
			BigDecimal price = (BigDecimal) super.dcf.parse(txtPrice.getValue());
			order.setPrice(price);
			order.setDateCreat(dtfDateCreat.getValue());
			order.setCompletionDate(dtfCompletionDate.getValue());
			order.setStatus(ntsStatus.getValue());
			ordersDao.create(order);
			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
