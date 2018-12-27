package com.haulmont.testtask.view;

import java.util.List;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.Orders;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class OrderView extends AbstractView implements View {
	private DaoFactory hsqlDaoFactory;
	private OrdersDao orderDao;
	private HorizontalLayout filterPanel;

	public OrderView() throws UiException {
		try {
			hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
			orderDao = hsqlDaoFactory.getOrderDao();
			List<Orders> orders = orderDao.findAll();
			TextField description = new TextField("Описание");
			TextField status = new TextField("Статус");
			TextField client = new TextField("Клиент");
			filterPanel = new HorizontalLayout(description, status, client);
			this.addComponent(filterPanel);
			Grid<Orders> grid = new Grid<>();
			grid.setWidth(100.0f, Unit.PERCENTAGE);
			grid.setItems(orders);
//			grid.addColumn(Orders::getId).setId("id").setCaption("№");
			grid.addColumn(Orders::getDescription).setId("description").setCaption("Описание");
			grid.addColumn(Orders::getClientId).setId("clientId").setCaption("Клиент");
			grid.addColumn(Orders::getMechanicId).setId("mechanicId").setCaption("Механик");
			grid.addColumn(Orders::getDateCreat).setId("dateCreat").setCaption("Дата создания заявки");
			grid.addColumn(Orders::getCompletionDate).setId("completionDate").setCaption("Дата окончания работ");
			grid.addColumn(Orders::getPrice).setId("price").setCaption("Цена");
			grid.addColumn(Orders::getStatus).setId("status").setCaption("Статус");
			grid.setColumnOrder("description", "clientId", "mechanicId", "status", "dateCreat", "completionDate",
					"price");
			this.addComponent(grid);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

//    private HorizontalLayout getFilterPanel() { 	
//        return filterPanel;
//    }

	@Override
	void btnAddClick() {
		// TODO Auto-generated method stub

	}

	@Override
	void btnChangeClick() {
		// TODO Auto-generated method stub

	}

	@Override
	void btnDeleteClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to Orders View");
	}

}
