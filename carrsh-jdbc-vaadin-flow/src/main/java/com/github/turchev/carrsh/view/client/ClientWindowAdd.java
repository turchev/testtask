//package com.github.turchev.carrsh.view.client;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.github.turchev.carrsh.domain.person.Client;
//import com.github.turchev.carrsh.view.UiException;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.data.binder.ValidationException;
//
//@SuppressWarnings("serial")
//class ClientWindowAdd extends ClientWindowAbstract {
//	private static final Logger LOG = LogManager.getLogger();
//
//	protected ClientWindowAdd() throws UiException {
//		super.setCaption("Создать запись о клиенте");
//		LOG.debug("Created ClientWindowAdd");
//	}
//
//	@Override
//	protected void btnCancelClick() {
//		close();
//	}
//
//	@Override
//	protected void btnAppleClick() {
//		try {
//			Client client = new Client();
//			super.binder.writeBean(client);
//			super.clientDao.create(client);
//			UI.getCurrent().getNavigator().navigateTo(ClientView.NAME);
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
