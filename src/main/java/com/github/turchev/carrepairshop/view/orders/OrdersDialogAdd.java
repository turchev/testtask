package com.github.turchev.carrepairshop.view.orders;

import com.vaadin.flow.data.validator.DateTimeRangeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.orders.OrderStatusType;
import com.github.turchev.carrepairshop.domain.orders.OrdersWithFio;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.data.binder.ValidationException;

import java.time.LocalDateTime;


@SuppressWarnings("serial")
class OrdersDialogAdd extends OrdersDialogAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Создание заявки";

	protected OrdersDialogAdd() throws UiException {
		try {
			super.add(new Label(LABEL));
			super.ntsStatus.setValue(OrderStatusType.Принят);

			/**
			 * При оформлении новой записи ограничения создания с текущей даты -1час до
			 * +10дней
			 */
			binder.forField(super.dtfDateCreate)
					.withValidator(new DateTimeRangeValidator("Введите корректную дату создания заявки",
							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusDays(10)))
					.bind(OrdersWithFio::getDateCreate, OrdersWithFio::setDateCreat);
			super.dtfDateCreate.setValue(LocalDateTime.now());

			/**
			 * При оформлении новой записи ограничения на завершения работ с текущей даты
			 * -1час до +5лет
			 */
			binder.forField(dtfCompletionDate)
					.withValidator(new DateTimeRangeValidator("Введите корректную дату завершения работ",
							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusYears(5)))
					.bind(OrdersWithFio::getCompletionDate, OrdersWithFio::setCompletionDate);

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
		if (txrDescription.isEmpty()) {
			Notification.show("Описание заявки не может быть пустым", 4000, Position.MIDDLE);
			return;
		}
		if (dtfDateCreate.isEmpty()) {
			Notification.show("Укажите дату и время создания заявки", 4000, Position.MIDDLE);
			return;
		}
		if (dtfCompletionDate.isEmpty()) {
			Notification.show("Укажите дату и время завершения работ", 4000, Position.MIDDLE);
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

		try {
			OrdersWithFio order = new OrdersWithFio();
			super.binder.writeBean(order);
			order.setClientId(cmbClient.getValue().getId());
			order.setMechanicId(cmbMechanic.getValue().getId());			
			order.setStatus(ntsStatus.getValue());
			super.ordersDao.create(order);
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
