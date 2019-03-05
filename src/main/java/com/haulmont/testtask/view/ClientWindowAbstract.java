package com.haulmont.testtask.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
abstract class ClientWindowAbstract extends Window {

	protected TextField txtFirstName, txtLastName, txtPatronnymic, txtPhone;

	protected ClientWindowAbstract() {
		txtLastName = new TextField("Фамилия");
		txtLastName.setSizeFull();
		txtFirstName = new TextField("Имя");
		txtFirstName.setSizeFull();
		txtPatronnymic = new TextField("Отчество");
		txtPatronnymic.setSizeFull();
		txtPhone = new TextField("Телефон");
		txtPhone.setSizeFull();
		VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtPhone);
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
