//package com.github.turchev.carrsh.view.orders;
//
//import java.time.LocalDateTime;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.github.turchev.carrsh.domain.orders.OrderStatusType;
//import com.github.turchev.carrsh.domain.orders.OrdersWithFio;
//import com.github.turchev.carrsh.view.UiException;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.data.binder.ValidationException;
//import com.vaadin.flow.data.validator.DateTimeRangeValidator;
//
//@SuppressWarnings("serial")
//class OrdersWindowAdd extends OrdersWindowAbstract {
//	private static final Logger LOG = LogManager.getLogger();
//
//	protected OrdersWindowAdd() throws UiException {
//		try {
//			super.setCaption("Создать заявку");
//			super.ntsStatus.setValue(OrderStatusType.Принят);
//
//			/**
//			 * При оформлении новой записи ограничения создания с текущей даты -1час до
//			 * +10дней
//			 */
//			binder.forField(super.dtfDateCreat)
//					.withValidator(new DateTimeRangeValidator("Введите корректную дату создания заявки",
//							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusDays(10)))
//					.bind(OrdersWithFio::getDateCreat, OrdersWithFio::setDateCreat);
//			super.dtfDateCreat.setValue(LocalDateTime.now());
//
//			/**
//			 * При оформлении новой записи ограничения на завершения работ с текущей даты
//			 * -1час до +5лет
//			 */
//			binder.forField(dtfCompletionDate)
//					.withValidator(new DateTimeRangeValidator("Введите корректную дату завершения работ",
//							LocalDateTime.now().minusHours(1), LocalDateTime.now().plusYears(5)))
//					.bind(OrdersWithFio::getCompletionDate, OrdersWithFio::setCompletionDate);
//
//		} catch (Exception e) {
//			throw new UiException(e);
//		}
//		LOG.debug("Created OrdersWindowAdd");
//	}
//
//	@Override
//	protected void btnCancelClick() {
//		close();
//	}
//
//	@Override
//	protected synchronized void btnAppleClick() {
//		if (txrDescription.isEmpty()) {
//			Notification.show("Описание заявки не может быть пустым", Type.WARNING_MESSAGE);
//			return;
//		}
//		if (cmbClient.isEmpty()) {
//			Notification.show("Выберите клиента из списка или создайте новую запись", Type.WARNING_MESSAGE);
//			return;
//		}
//		if (cmbMechanic.isEmpty()) {
//			Notification.show("Выберите механика из списка или создайте новую запись", Type.WARNING_MESSAGE);
//			return;
//		}
//		if (ntsStatus.isEmpty()) {
//			Notification.show("Задайте статус заявки", Type.WARNING_MESSAGE);
//			return;
//		}
//		if (dtfDateCreat.isEmpty()) {
//			Notification.show("Укажите дату заявки", Type.WARNING_MESSAGE);
//			return;
//		}
//
//		try {
//			OrdersWithFio order = new OrdersWithFio();
//			super.binder.writeBean(order);
//			order.setClientId(cmbClient.getValue().getId());
//			order.setMechanicId(cmbMechanic.getValue().getId());
//			;
//			order.setStatus(ntsStatus.getValue());
//			super.ordersDao.create(order);
//			UI.getCurrent().getNavigator().navigateTo(OrdersView.NAME);
//			close();
//		} catch (ValidationException ev) {
//			LOG.debug(ev);
//			Notification.show("Проверьте корректность заполнения полей данных");
//		} catch (Exception e) {
//			LOG.error(e);
//			Notification.show("Не удалось сохранить запись");
//		}
//	}
//}
