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
import com.haulmont.testtask.domain.orders.OrdersWithFio;
import com.haulmont.testtask.domain.person.Client;
import com.haulmont.testtask.domain.person.Mechanic;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.view.UiException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class OrdersWindowEdit extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private OrdersDao ordersDao;
	private ClientDao clientDao;
	private MechanicDao mechanicDao;
	private Long id;
	List<ShortName<Mechanic>> mechanicShortName;
	List<ShortName<Client>> clientShortName;

	protected OrdersWindowEdit(Long id) throws UiException {
		try {
			super.setCaption("Редактировать заявку");
			this.id = id;
			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
			clientDao = DaoFactory.getFactory(DsType.HSQLDB).getClientDao();
			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();

			OrdersWithFio order = ordersDao.findById(id);
			mechanicShortName = mechanicDao.findAllShortName();
			ShortName<Mechanic> mechanicFio = mechanicDao.getFioById(order.getMechanicId());
			ShortName<Client> clientFio = clientDao.getFioById(order.getClientId());
			clientShortName = clientDao.findAllShortName();
			super.cmbClient.setItems(clientShortName);
			super.cmbMechanic.setItems(mechanicShortName);			
			cmbClient.setSelectedItem(clientFio);
			cmbMechanic.setSelectedItem(mechanicFio);
			ntsStatus.setValue(order.getStatus());
			dtfDateCreat.setValue(order.getDateCreat());
			dtfCompletionDate.setValue(order.getCompletionDate());
			txtPrice.setValue(order.getPrice().toString());
			txrDescription.setValue(order.getDescription());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created OrdersWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {

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
			order.setId(id);
			ordersDao.update(order);
			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
