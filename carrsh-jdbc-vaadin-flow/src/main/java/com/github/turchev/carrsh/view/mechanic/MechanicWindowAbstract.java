//package com.github.turchev.carrsh.view.mechanic;
//
//import java.awt.Window;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.Locale;
//
//import com.github.turchev.carrsh.dao.DaoFactory;
//import com.github.turchev.carrsh.dao.MechanicDao;
//import com.github.turchev.carrsh.domain.person.Mechanic;
//import com.github.turchev.carrsh.ds.DsType;
//import com.github.turchev.carrsh.view.UiException;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
//import com.vaadin.flow.data.validator.BigDecimalRangeValidator;
//import com.vaadin.flow.data.validator.RegexpValidator;
//import com.vaadin.flow.data.validator.StringLengthValidator;
//import com.vaadin.flow.data.value.ValueChangeMode;
//
//import elemental.css.CSSStyleDeclaration.Unit;
//
//@SuppressWarnings("serial")
//abstract class MechanicWindowAbstract extends Window {
//
//	protected TextField txtLastName, txtFirstName, txtPatronnymic, txtWages;
//	protected DecimalFormat dcf = new DecimalFormat();
//	protected MechanicDao mechanicDao;
//	protected Binder<Mechanic> binder;
//
//	protected MechanicWindowAbstract() throws UiException {
//		try {
//			dcf.setParseBigDecimal(true);
//			mechanicDao = DaoFactory.getFactory(DsType.HSQLDB).getMechanicDao();
//			binder = new Binder<>(Mechanic.class);
//			binder.setBean(new Mechanic());
//
//			txtLastName = new TextField("Фамилия");
//			binder.forField(txtLastName)
//					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
//							"[а-яА-Я]+-?[а-яА-Я]+"))
//					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
//					.bind(Mechanic::getLastName, Mechanic::setLastName);
//			txtLastName.setSizeFull();
//
//			txtFirstName = new TextField("Имя");
//			binder.forField(txtFirstName)
//					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
//							"[а-яА-Я]+-?[а-яА-Я]+"))
//					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
//					.bind(Mechanic::getFirstName, Mechanic::setFirstName);
//			txtFirstName.setSizeFull();
//
//			txtPatronnymic = new TextField("Отчество");
//			binder.forField(txtPatronnymic)
//					.withValidator(new RegexpValidator("Допустимы только символы русского алфавита и дефис ",
//							"[а-яА-Я]+-?[а-яА-Я]+"))
//					.withValidator(new StringLengthValidator("Максимум 40 символов", 0, 40))
//					.bind(Mechanic::getPatronnymic, Mechanic::setPatronnymic);
//			txtPatronnymic.setSizeFull();
//
//			txtWages = new TextField("Почасовая оплата");
//			txtWages.setLocale(new Locale("en", "US"));
//			txtWages.setValueChangeMode(ValueChangeMode.EAGER);
//			binder.forField(txtWages).withNullRepresentation("")
//					.withConverter(new StringToBigDecimalConverter("Введите сумму в формате 00000.00"))
//					.withValidator(new BigDecimalRangeValidator("Столько механики не зарабатывают", new BigDecimal(10),
//							new BigDecimal(10000)))
//					.bind(Mechanic::getWages, Mechanic::setWages);
//			txtWages.setSizeFull();
//
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
//		} catch (Exception e) {
//			throw new UiException(e);
//		}
//	}
//
//	protected abstract void btnCancelClick();
//
//	protected abstract void btnAppleClick();
//
//}
