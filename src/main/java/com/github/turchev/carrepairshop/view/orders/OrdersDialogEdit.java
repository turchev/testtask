package com.github.turchev.carrepairshop.view.orders;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.validator.DateTimeRangeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.ShortName;
import com.github.turchev.carrepairshop.domain.orders.OrdersWithFio;
import com.github.turchev.carrepairshop.domain.person.Client;
import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.data.binder.ValidationException;

import java.time.LocalDateTime;


@SuppressWarnings("serial")
class OrdersDialogEdit extends OrdersDialogAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Редактирование заявки";
	private Long id;

	protected OrdersDialogEdit(Long id) throws UiException {
		try {
			super.add(new Label(LABEL));
			this.id = id;
			OrdersWithFio order = ordersDao.findById(id);
			ShortName<Mechanic> mechanicFio = super.mechanicDao.getFioById(order.getMechanicId());
			ShortName<Client> clientFio = super.clientDao.getFioById(order.getClientId());
			cmbClient.setValue(clientFio);
			cmbMechanic.setValue(mechanicFio);
	
			/**
			 * При редактировании записи ограничения создания заявки -5 лет от текущей даты
			 * до +5 лет
			 */
			binder.forField(dtfDateCreate)
					.withValidator(new DateTimeRangeValidator("Дата создания вне диапазона",
							LocalDateTime.now().minusYears(5), LocalDateTime.now().plusYears(5)))
					.bind(OrdersWithFio::getDateCreate, OrdersWithFio::setDateCreat);

			/**
			 * При редактировании записи ограничения даты завершения работ -5 лет от текущей
			 * даты до +5 лет
			 */
			binder.forField(dtfCompletionDate)
					.withValidator(new DateTimeRangeValidator("Дата завершения работ вне диапазона",
							LocalDateTime.now().minusYears(5), LocalDateTime.now().plusYears(5)))
					.bind(OrdersWithFio::getCompletionDate, OrdersWithFio::setCompletionDate);

			txrDescription.setValue(order.getDescription());
			dtfDateCreate.setValue(order.getDateCreate());
			dtfCompletionDate.setValue(order.getCompletionDate());
			ntsStatus.setValue(order.getStatus());
			dcfPrice.setValue(order.getPrice());
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
			Notification.show("Описание заявки не может быть пустым", 4000, Position.MIDDLE);
			return;
		}
		if (cmbClient.isEmpty()) {
			Notification.show("Выберите клиента из списка или создайте новую запись", 4000, Position.MIDDLE);
			return;
		}
		if (cmbMechanic.isEmpty()) {
			Notification.show("Выберите механика из списка или создайте новую запись", 4000, Position.MIDDLE);
			return;
		}
		if (ntsStatus.isEmpty()) {
			Notification.show("Задайте статус заявки", 4000, Position.MIDDLE);
			return;
		}
		if (dtfDateCreate.isEmpty()) {
			Notification.show("Укажите дату заявки", 4000, Position.MIDDLE);
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
