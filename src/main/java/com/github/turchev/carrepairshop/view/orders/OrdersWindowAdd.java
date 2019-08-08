package com.github.turchev.carrepairshop.view.orders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrepairshop.domain.orders.OrderStatusType;
import com.github.turchev.carrepairshop.domain.orders.OrdersWithFio;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.data.binder.ValidationException;


@SuppressWarnings("serial")
class OrdersWindowAdd extends OrdersWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private static final String LABEL = "Создание заявки";

	protected OrdersWindowAdd() throws UiException {
		try {
			super.add(new Label(LABEL));
			super.ntsStatus.setValue(OrderStatusType.Принят);

			/**
			 * При оформлении новой записи ограничения создания с текущей даты -1час до
			 * +10дней
			 */
//			binder.forField(super.dtfDateCreat)
//					.withValidator(new DateTimeRangeValidator("Введите корректную дату создания заявки",
//							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusDays(10)))
//					.bind(OrdersWithFio::getDateCreat, OrdersWithFio::setDateCreat);
//			super.dtfDateCreat.setValue(LocalDateTime.now());

			/**
			 * При оформлении новой записи ограничения на завершения работ с текущей даты
			 * -1час до +5лет
			 */
//			binder.forField(dtfCompletionDate)
//					.withValidator(new DateTimeRangeValidator("Введите корректную дату завершения работ",
//							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusYears(5)))
//					.bind(OrdersWithFio::getCompletionDate, OrdersWithFio::setCompletionDate);

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
		if (dtfDateCreat.isEmpty()) {
			Notification.show("Укажите дату заявки", 4000, Position.MIDDLE);
			return;
		}

		try {
			OrdersWithFio order = new OrdersWithFio();
			super.binder.writeBean(order);
			order.setClientId(cmbClient.getValue().getId());
			order.setMechanicId(cmbMechanic.getValue().getId());			
			order.setStatus(ntsStatus.getValue());
			super.ordersDao.create(order);
//			btnOrders.addClickListener(e -> {
//				btnOrders.getUI().ifPresent(ui -> ui.navigate(OrdersView.NAME));
//			});
			UI.getCurrent().navigate(OrdersView.NAME);
//			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
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
