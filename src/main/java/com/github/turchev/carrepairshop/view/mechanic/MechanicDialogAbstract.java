package com.github.turchev.carrepairshop.view.mechanic;

import java.math.BigDecimal;

import com.github.turchev.carrepairshop.dao.DaoFactory;
import com.github.turchev.carrepairshop.dao.MechanicDao;
import com.github.turchev.carrepairshop.domain.person.Mechanic;
import com.github.turchev.carrepairshop.ds.DsType;
import com.github.turchev.carrepairshop.view.UiException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.BigDecimalRangeValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;

@SuppressWarnings("serial")
abstract class MechanicDialogAbstract extends Dialog {

	protected TextField txtLastName, txtFirstName, txtPatronnymic;
	protected BigDecimalField dcfWages;
	protected MechanicDao mechanicDao;
	protected Binder<Mechanic> binder;

	protected MechanicDialogAbstract() throws UiException {
		try {
			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
			binder = new Binder<>(Mechanic.class);
			binder.setBean(new Mechanic());

			txtLastName = new TextField("Фамилия");
			binder.forField(txtLastName)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Mechanic::getLastName, Mechanic::setLastName);
			txtLastName.setSizeFull();

			txtFirstName = new TextField("Имя");
			binder.forField(txtFirstName)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Mechanic::getFirstName, Mechanic::setFirstName);
			txtFirstName.setSizeFull();

			txtPatronnymic = new TextField("Отчество");
			binder.forField(txtPatronnymic)
					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
							"[а-яА-Я]+-?[а-яА-Я]+"))
					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
					.bind(Mechanic::getPatronnymic, Mechanic::setPatronnymic);
			txtPatronnymic.setSizeFull();

			dcfWages = new BigDecimalField("Почасовая оплата");
			dcfWages.setValueChangeMode(ValueChangeMode.EAGER);
			binder.forField(dcfWages)
					.withValidator(new BigDecimalRangeValidator("Столько механики не зарабатывают", new BigDecimal(10),
							new BigDecimal(10000)))
					.bind(Mechanic::getWages, Mechanic::setWages);
			dcfWages.setSizeFull();

			VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, dcfWages);
			Button btnApple = new Button("Ok");
			btnApple.addClickListener(event -> {
				btnAppleClick();
			});
			Button btnCancel = new Button("Отменить");
			btnCancel.addClickListener(event -> {
				btnCancelClick();
			});
			HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
			vlLayout.add(hLayout);
			this.add(vlLayout);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}
