package com.haulmont.testtask.view;

import java.math.BigDecimal;
import java.text.ParseException;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class MechanicWindowEdit extends MechanicWindowAbstract {
	private Long id;

	public MechanicWindowEdit(Long id) {
		super.setCaption("Редактировать данные механика");
		this.id = id;		 
		try {
			Mechanic mechanic = mechanicDao.findById(this.id);
			super.txtFirstName.setValue(mechanic.getFirstName());
			super.txtLastName.setValue(mechanic.getLastName());
			super.txtPatronnymic.setValue(mechanic.getPatronnymic());			
			super.txtWages.setValue(mechanic.getWages().toString());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Override
	protected void btnCancelClick() {
		close();
	}

	@Override
	protected synchronized void btnAppleClick() {		
		try {
			Mechanic mechanic = new Mechanic(txtLastName.getValue(), txtFirstName.getValue(), txtPatronnymic.getValue());	
			mechanic.setWages((BigDecimal) super.dcf.parse(txtWages.getValue()));
			mechanic.setId(id);
			mechanicDao.update(mechanic);
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
