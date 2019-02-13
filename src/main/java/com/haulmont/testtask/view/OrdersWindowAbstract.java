package com.haulmont.testtask.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrderStatusType;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public abstract class OrdersWindowAbstract extends Window {
	private static final Logger LOG = LogManager.getLogger();
	protected OrdersDao ordersDao;
	protected ComboBox<String> cmbClient, cmbMechanic;
	protected NativeSelect<OrderStatusType> ntsStatus;
	protected Label lblDateCreat, lblCompletionDate;
	protected DateTimeField dtfDateCreat, dtfCompletionDate;
	protected TextField txtPrice;

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
//		txtLastName = new TextField("Фамилия");
//		txtLastName.setSizeFull();
//		txtFirstName = new TextField("Имя");
//		txtFirstName.setSizeFull();		
//		txtPatronnymic = new TextField("Отчество");
//		txtPatronnymic.setSizeFull();
//		txtPhone = new TextField("Телефон");
//		txtPhone.setSizeFull();
//		VerticalLayout vlLayout = new VerticalLayout(txtLastName, txtFirstName, txtPatronnymic, txtPhone);
//		this.setWidth(400.0f, Unit.PIXELS);
//		this.setModal(true);
//		this.setResizable(false);
//		Button btnApple = new Button("Ok");
//		btnApple.addClickListener(event -> {
//			btnAppleClick();
//		});
//		Button btnCancel = new Button("Отменить");
//		btnCancel.addClickListener(event -> {
//			btnCancelClick();
//		});
//		HorizontalLayout hLayout = new HorizontalLayout(btnApple, btnCancel);
//		vlLayout.addComponent(hLayout);
//		this.setContent(vlLayout);
	}

	protected abstract void btnCancelClick();

	protected abstract void btnAppleClick();

}
