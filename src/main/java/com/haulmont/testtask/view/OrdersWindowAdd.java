package com.haulmont.testtask.view;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrderStatusType;
import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class OrdersWindowAdd extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private OrdersDao ordersDao;
	private List<OrdersWithFio> ordersWithFio;

	public OrdersWindowAdd() throws UiException {
		try {
			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
			ordersWithFio = ordersDao.findAll();
			super.setCaption("Создать заявку");
			super.cmbClient.setItems(ordersWithFio);
			super.cmbMechanic.setItems(ordersWithFio);
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
			OrdersWithFio order = new OrdersWithFio(txrDescription.getValue(), cmbClient.getValue().getClientId(),
					cmbMechanic.getValue().getMechanicId());
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
