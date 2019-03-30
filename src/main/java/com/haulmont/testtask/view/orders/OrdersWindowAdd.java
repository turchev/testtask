package com.haulmont.testtask.view.orders;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.ClientDao;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.domain.ShortName;
import com.haulmont.testtask.domain.orders.OrderStatusType;
import com.haulmont.testtask.domain.orders.OrdersWithFio;
import com.haulmont.testtask.domain.person.Client;
import com.haulmont.testtask.domain.person.Mechanic;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.view.UiException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class OrdersWindowAdd extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private OrdersDao ordersDao;
	private MechanicDao mechanicDao;
	private ClientDao clientDao;
	List<ShortName<Mechanic>> mechanicShortName;
	List<ShortName<Client>> clientShortName;

	protected OrdersWindowAdd() throws UiException {
		try {
			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
			mechanicShortName = mechanicDao.findAllShortName();
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDao();
			clientShortName = clientDao.findAllShortName();
			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
			super.setCaption("Создать заявку");
			super.cmbClient.setItems(clientShortName);
			super.cmbMechanic.setItems(mechanicShortName);
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
