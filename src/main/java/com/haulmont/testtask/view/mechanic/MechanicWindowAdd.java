package com.haulmont.testtask.view.mechanic;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.domain.person.Mechanic;
import com.haulmont.testtask.view.UiException;
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
			Mechanic mechanic = new Mechanic(txtLastName.getValue(), txtFirstName.getValue(),
					txtPatronnymic.getValue());
			BigDecimal wages = (BigDecimal) super.dcf.parse(txtWages.getValue());
			mechanic.setWages(wages);
			super.mechanicDao.create(mechanic);
			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
