package com.haulmont.testtask.view;

import java.text.DecimalFormat;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
abstract class MechanicWindowAbstract extends Window {

	protected TextField txtLastName, txtFirstName, txtPatronnymic, txtWages;
	protected DecimalFormat dcf = new DecimalFormat();

	protected MechanicWindowAbstract() {
		dcf.setParseBigDecimal(true);
		txtLastName = new TextField("Фамилия");
		txtLastName.setSizeFull();
		txtFirstName = new TextField("Имя");
		txtFirstName.setSizeFull();
		txtPatronnymic = new TextField("Отчество");
		txtPatronnymic.setSizeFull();
		txtWages = new TextField("Почасовая оплата");
		txtWages.setSizeFull();
		VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtWages);
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
