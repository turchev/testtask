package com.haulmont.testtask.view;

import java.math.BigDecimal;
import java.text.ParseException;
import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MechanicWindowAdd extends MechanicWindowAbstract {

	public MechanicWindowAdd() {
		super.setCaption("Создать запись о механике");
	}

	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected void btnAppleClick() {
		Mechanic mechanic = new Mechanic(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());
		try {
			BigDecimal wages = (BigDecimal) super.dcf.parse(txtWages.getValue());
			mechanic.setWages(wages);
			mechanicDao.create(mechanic);
			UI.getCurrent().getNavigator().navigateTo("mechanic");
			close();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
