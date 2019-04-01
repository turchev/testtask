package com.haulmont.testtask.view.mechanic;

import com.haulmont.testtask.domain.person.MechanicTest;
import com.haulmont.testtask.view.UiException;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MechanicWindowsTest extends Window {
	private static final long serialVersionUID = 2560604286775402891L;
	private TextField txtLastName = new TextField("Фамилия");
	private TextField txtFirstName = new TextField("Имя");
	private TextField txtPatronnymic = new TextField("Отчество");
	private TextField txtInteger = new TextField("Test Integer");
	private TextField txtWages = new TextField("Почасовая оплата");
	Binder<MechanicTest> binder = new Binder<>(MechanicTest.class);

	public MechanicWindowsTest() throws UiException {
		binderInit();
	}

	private void binderInit() throws UiException {
		try {
			binder.setBean(new MechanicTest());

			binder.forField(txtLastName)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(MechanicTest::getLastName, MechanicTest::setLastName);

			binder.forField(txtFirstName)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(MechanicTest::getFirstName, MechanicTest::setFirstName);

			binder.forField(txtInteger).withConverter(new StringToIntegerConverter("Falsches Format"))
					.bind(MechanicTest::getTxtInteger, MechanicTest::setTxtInteger);

			binder.forField(txtPatronnymic)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(MechanicTest::getPatronnymic, MechanicTest::setPatronnymic);

			binder.forField(txtWages).withConverter(new StringToBigDecimalConverter("Falsches Format"))
					.bind(MechanicTest::getWages, MechanicTest::setWages);

			VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtInteger,
					txtWages);			
			this.setContent(vlLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
