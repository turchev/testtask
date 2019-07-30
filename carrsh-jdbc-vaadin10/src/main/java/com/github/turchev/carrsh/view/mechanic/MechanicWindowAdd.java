package com.github.turchev.carrsh.view.mechanic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrsh.domain.person.Mechanic;
import com.github.turchev.carrsh.view.UiException;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class MechanicWindowAdd extends MechanicWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();

	protected MechanicWindowAdd() throws UiException {
		try {
			super.setCaption("Создать запись о механике");
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowAdd");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		try {
			Mechanic mechanic = new Mechanic();
			binder.writeBean(mechanic);
			super.mechanicDao.create(mechanic);
			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
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
