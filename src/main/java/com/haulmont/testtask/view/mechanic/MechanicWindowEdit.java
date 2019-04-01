package com.haulmont.testtask.view.mechanic;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.domain.person.Mechanic;
import com.haulmont.testtask.view.UiException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
class MechanicWindowEdit extends MechanicWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private Long id;

	protected MechanicWindowEdit(Long id) throws UiException {
		try {
			super.setCaption("Редактировать данные механика");
			this.id = id;
			Mechanic mechanic = super.mechanicDao.findById(id);
			super.txtFirstName.setValue(mechanic.getFirstName());
			super.txtLastName.setValue(mechanic.getLastName());
			super.txtPatronnymic.setValue(mechanic.getPatronnymic());
			super.txtWages.setValue(mechanic.getWages().toString());
		} catch (Exception e) {
			throw new UiException(e);
		}
		LOG.debug("Created MechanicWindowEdit");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {
		try {
			Mechanic mechanic = new Mechanic(super.txtLastName.getValue(), super.txtFirstName.getValue(),
					super.txtPatronnymic.getValue());
			mechanic.setWages((BigDecimal) super.dcf.parse(super.txtWages.getValue()));
			mechanic.setId(id);
			super.mechanicDao.update(mechanic);
			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
