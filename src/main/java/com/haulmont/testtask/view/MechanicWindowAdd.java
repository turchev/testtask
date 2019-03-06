package com.haulmont.testtask.view;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MechanicWindowAdd extends MechanicWindowAbstract {
	private static final Logger LOG = LogManager.getLogger();
	private MechanicDao mechanicDao;

	public MechanicWindowAdd() throws UiException {
		super.setCaption("Создать запись о механике");
		try {
			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
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
			mechanicDao.create(mechanic);
			UI.getCurrent().getNavigator().navigateTo(MechanicView.NAME);
			close();
		} catch (Exception e) {
			LOG.error(e);
			Notification.show("Не удалось сохранить запись");
		}
	}
}
