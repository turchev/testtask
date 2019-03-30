package com.haulmont.testtask.view.orders;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.haulmont.testtask.domain.ShortName;
import com.haulmont.testtask.domain.orders.OrderStatusType;
import com.haulmont.testtask.domain.person.Client;
import com.haulmont.testtask.domain.person.Mechanic;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
abstract class OrdersWindowAbstract extends Window {	
	protected ComboBox<ShortName<Client>> cmbClient;
	protected ComboBox<ShortName<Mechanic>> cmbMechanic;
	protected NativeSelect<OrderStatusType> ntsStatus;
	protected DateTimeField dtfDateCreat, dtfCompletionDate;
	protected TextField txtPrice;
	protected TextArea txrDescription;
	protected DecimalFormat dcf = new DecimalFormat();	
	protected List<ShortName<Mechanic>> mechanicShortName;
	protected List<ShortName<Client>> clientShortName;

	protected OrdersWindowAbstract() {
		
		dcf.setParseBigDecimal(true);
		ntsStatus = new NativeSelect<>("Статус");
		ntsStatus.setItems(OrderStatusType.values());
		cmbClient = new ComboBox<>("Клиент ФИО");		
//		cmbClient.setItemCaptionGenerator(ShortName<Client>::getFio);
		cmbClient.setWidth(300.0f, Unit.PIXELS);
		cmbMechanic = new ComboBox<>("Механик ФИО");		
//		cmbMechanic.setItemCaptionGenerator(ShortName<Mechanic>::getFio);
		cmbMechanic.setWidth(300.0f, Unit.PIXELS);
		dtfDateCreat = new DateTimeField("Дата создания заявки");
		dtfDateCreat.setValue(LocalDateTime.now());
		dtfCompletionDate = new DateTimeField("Дата окончания работ");
		txtPrice = new TextField("Цена");
		txrDescription = new TextArea("Описание заявки");
		txrDescription.setSizeFull();
		HorizontalLayout hltStatusPrice = new HorizontalLayout(ntsStatus, txtPrice);
		HorizontalLayout hltDate = new HorizontalLayout(dtfDateCreat, dtfCompletionDate);
		HorizontalLayout hltClientMechanic = new HorizontalLayout(cmbClient, cmbMechanic);
		VerticalLayout vlLayout = new VerticalLayout(txrDescription, hltStatusPrice, hltDate, hltClientMechanic);
		this.setWidth(800.0f, Unit.PIXELS);
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
		HorizontalLayout hltButton = new HorizontalLayout(btnApple, btnCancel);
		vlLayout.addComponent(hltButton);
		this.setContent(vlLayout);

	}
	
	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}
