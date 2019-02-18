package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrderStatusType;
import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class OrdersWindowAbstract extends Window {
	private static final Logger LOG = LogManager.getLogger();
	protected OrdersDao ordersDao;
	protected ComboBox<OrdersWithFio> cmbClient, cmbMechanic;
	protected NativeSelect<OrderStatusType> ntsStatus;
//	protected Label lblDateCreat, lblCompletionDate;
	protected DateTimeField dtfDateCreat, dtfCompletionDate;
	protected TextField txtPrice;
	protected TextArea txrDescription;

	public OrdersWindowAbstract() {
		try {
			ordersDao = DaoFactory.getFactory(DsType.HSQLDB).getOrdersDao();
		} catch (Exception e) {
			Notification.show("Не удалось загрузить из источника данных", Type.ERROR_MESSAGE);
			LOG.error(e);
			close();
		}
		init();
	}

	private void init() {
		ntsStatus = new NativeSelect<>("Статус");
//		ntsStatus.setSizeFull();		
		cmbClient = new ComboBox<>("Клиент ФИО");
//		cmbClient.setSizeFull();
		cmbMechanic = new ComboBox<>("Механик ФИО");
//		cmbMechanic.setSizeFull();	
		dtfDateCreat = new DateTimeField("Дата создания заявки");
//		dtfDateCreat.setValue(LocalDateTime.now());
		dtfCompletionDate = new DateTimeField("Дата окончания работ");
		txtPrice = new TextField("Цена");
		txrDescription = new TextArea("Описание заявки");
		txrDescription.setSizeFull();
		HorizontalLayout hltDate = new HorizontalLayout(dtfDateCreat, dtfCompletionDate);
		HorizontalLayout hltClientMechanic = new HorizontalLayout(cmbClient, cmbMechanic);

		VerticalLayout vlLayout = new VerticalLayout(txrDescription, ntsStatus, hltDate, hltClientMechanic);
		this.setWidth(600.0f, Unit.PIXELS);
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
