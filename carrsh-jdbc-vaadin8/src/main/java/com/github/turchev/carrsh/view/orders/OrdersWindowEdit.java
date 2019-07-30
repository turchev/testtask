package com.github.turchev.carrsh.view.orders;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrsh.domain.ShortName;
import com.github.turchev.carrsh.domain.orders.OrdersWithFio;
import com.github.turchev.carrsh.domain.person.Client;
import com.github.turchev.carrsh.domain.person.Mechanic;
import com.github.turchev.carrsh.view.UiException;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.DateTimeRangeValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class OrdersWindowEdit extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private Long id;

	protected OrdersWindowEdit(Long id) throws UiException {
		try {
			super.setCaption("Редактировать заявку");
			this.id = id;
			OrdersWithFio order = ordersDao.findById(id);
			ShortName<Mechanic> mechanicFio = super.mechanicDao.getFioById(order.getMechanicId());
			ShortName<Client> clientFio = super.clientDao.getFioById(order.getClientId());
			cmbClient.setSelectedItem(clientFio);
			cmbMechanic.setSelectedItem(mechanicFio);

			/**
			 * При редактировании записи ограничения создания заявки -5 лет от текущей даты
			 * до +5 лет
			 */
			binder.forField(dtfDateCreat)
					.withValidator(new DateTimeRangeValidator("Дата создания вне диапазона",
							LocalDateTime.now().minusYears(5), LocalDateTime.now().plusYears(5)))
					.bind(OrdersWithFio::getDateCreat, OrdersWithFio::setDateCreat);

			/**
			 * При редактировании записи ограничения даты завершения работ -5 лет от текущей
			 * даты до +5 лет
			 */
			binder.forField(dtfCompletionDate)
					.withValidator(new DateTimeRangeValidator("Дата завершения работ вне диапазона",
							LocalDateTime.now().minusYears(5), LocalDateTime.now().plusYears(5)))
					.bind(OrdersWithFio::getCompletionDate, OrdersWithFio::setCompletionDate);

			txrDescription.setValue(order.getDescription());
			dtfDateCreat.setValue(order.getDateCreat());
			dtfCompletionDate.setValue(order.getCompletionDate());
			ntsStatus.setValue(order.getStatus());
			txtPrice.setValue(order.getPrice().toString());
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
		if (txrDescription.isEmpty()) {
			Notification.show("Описание заявки не может быть пустым", Type.WARNING_MESSAGE);
			return;
		}
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
		if (dtfDateCreat.isEmpty()) {
			Notification.show("Укажите дату заявки", Type.WARNING_MESSAGE);
			return;
		}

		try {
			OrdersWithFio order = new OrdersWithFio();
			super.binder.writeBean(order);
			order.setClientId(cmbClient.getValue().getId());
			order.setMechanicId(cmbMechanic.getValue().getId());
			order.setStatus(ntsStatus.getValue());
			order.setId(id);
			super.ordersDao.update(order);
			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
			close();
		} catch (ValidationException ev) {
			LOG.debug(ev);
			Notification.show("Проверьте корректность заполнения полей данных");
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
