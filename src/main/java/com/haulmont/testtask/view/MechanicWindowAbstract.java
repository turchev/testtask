package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.ds.DsType;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class MechanicWindowAbstract extends Window {
	private static final Logger LOG = LogManager.getLogger();
	protected MechanicDao mechanicDao;	
	protected TextField txtLastName, txtFirstName, txtPatronnymic, txtWages;

	public MechanicWindowAbstract() {
		try {
			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
		} catch (Exception e) {
			LOG.error("No mechanic data source", e);
			close();
		}
		init();
	}

	private void init() {
		txtFirstName = new TextField("Имя");
		txtFirstName.setSizeFull();
		txtLastName = new TextField("Фамилия");
		txtLastName.setSizeFull();
		txtPatronnymic = new TextField("Отчество");
		txtPatronnymic.setSizeFull();
		txtWages = new TextField("Почасовая оплата");
		txtWages.setSizeFull();
		VerticalLayout vlLayout = new VerticalLayout(txtFirstName, txtLastName, txtPatronnymic, txtWages);
		this.setWidth(400.0f, Unit.PIXELS);
		this.setModal(true);
		this.setResizable(false);
		Button btnApple = new Button("Ok");
		btnApple.addClickListener(event -> {
			btnAppleClick();
		});
		Button btnCancel = new Button("Отменить");
		btnCancel.addClickListener(event -> {
			btnCancelClick();
		});
		HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
		vlLayout.addComponent(hLayout);
		this.setContent(vlLayout);
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}
