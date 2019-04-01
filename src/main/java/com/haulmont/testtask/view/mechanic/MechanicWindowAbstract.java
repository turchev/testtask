package com.haulmont.testtask.view.mechanic;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.MechanicDao;
import com.haulmont.testtask.domain.person.Mechanic;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.view.UiException;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.validator.BigDecimalRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
abstract class MechanicWindowAbstract extends Window {

	protected TextField txtLastName, txtFirstName, txtPatronnymic, txtWages;
	protected DecimalFormat dcf = new DecimalFormat();
	protected MechanicDao mechanicDao;

	protected MechanicWindowAbstract() throws UiException {
		try {
//			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
//			dcf.setParseBigDecimal(true);
//			txtLastName = new TextField("Фамилия");
//			txtLastName.setSizeFull();
//			txtFirstName = new TextField("Имя");
//			txtFirstName.setSizeFull();
//			txtPatronnymic = new TextField("Отчество");
//			txtPatronnymic.setSizeFull();
//			txtWages = new TextField("Почасовая оплата");
//			txtWages.setSizeFull();
//			VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtWages);
//			this.setWidth(400.0f, Unit.PIXELS);
//			this.setModal(true);
//			this.setResizable(false);
//			Button btnApple = new Button("Ok");
//			btnApple.addClickListener(event -> {
//				btnAppleClick();
//			});
//			Button btnCancel = new Button("Отменить");
//			btnCancel.addClickListener(event -> {
//				btnCancelClick();
//			});
//			HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
//			vlLayout.addComponent(hLayout);
//			this.setContent(vlLayout);

			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
			dcf.setParseBigDecimal(true);

			Binder<Mechanic> binder = new Binder<>(Mechanic.class);
			binder.setBean(new Mechanic());

			txtLastName = new TextField("Фамилия");
			binder.forField(txtLastName)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(Mechanic::getLastName, Mechanic::setLastName);
			txtLastName.setSizeFull();

			txtFirstName = new TextField("Имя");
			binder.forField(txtFirstName)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(Mechanic::getFirstName, Mechanic::setFirstName);
			txtFirstName.setSizeFull();

			txtPatronnymic = new TextField("Отчество");
			binder.forField(txtPatronnymic)
					.withValidator(new StringLengthValidator("Maximum of 40 characters allowed", 0, 40))
					.bind(Mechanic::getPatronnymic, Mechanic::setPatronnymic);
			txtPatronnymic.setSizeFull();

			txtWages = new TextField("Почасовая оплата");
//			txtWages.setValueChangeMode(ValueChangeMode.EAGER);
//			txtWages.setValue("15,00");
			try {				
				binder.forField(txtWages)
						.withConverter(new StringToBigDecimalConverter("Falsches Format"))
//						.withValidator(new BigDecimalRangeValidator("Столько не зарабатывают", new BigDecimal(10.00),
//								new BigDecimal(10000.00)))
						.bind(Mechanic::getWages, Mechanic::setWages);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}
