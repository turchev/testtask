package com.haulmont.testtask.view;

import java.util.List;

import com.haulmont.testtask.dao.DaoException;
import com.haulmont.testtask.dao.DaoFactory;
import com.haulmont.testtask.dao.OrdersDao;
import com.haulmont.testtask.ds.DsType;
import com.haulmont.testtask.entity.OrdersWithFio;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderRow;

@SuppressWarnings("serial")
public class OrderView extends AbstractView implements View {
	private DaoFactory hsqlDaoFactory;
	private OrdersDao orderDao;
	private HorizontalLayout filterPanel;

	public OrderView() throws DaoException, UiException {
		hsqlDaoFactory = DaoFactory.getFactory(DsType.HSQLDB);
		orderDao = hsqlDaoFactory.getOrderDao();
		TextField description = new TextField("Описание");
		TextField status = new TextField("Статус");
		TextField client = new TextField("Клиент");
		filterPanel = new HorizontalLayout(description, status, client);
		this.addComponent(filterPanel);
		refresh();
	}

	public void refresh() throws UiException  {
		try {			
			List<OrdersWithFio> orders = orderDao.findAll();
			Grid<OrdersWithFio> grid = new Grid<>();
			grid.setWidth(100.0f, Unit.PERCENTAGE);		
			grid.setSelectionMode(SelectionMode.SINGLE);
			HeaderRow filterRow = grid.appendHeaderRow();
			grid.setItems(orders);
			grid.addColumn(OrdersWithFio::getId).setId("id").setCaption("№");
			grid.addColumn(OrdersWithFio::getDescription).setId("description").setCaption("Описание").setWidth(500);
			grid.addColumn(OrdersWithFio::getClientFio).setId("clientFio").setCaption("Клиент ФИО");
			grid.addColumn(OrdersWithFio::getMechanicFio).setId("mechanicFio").setCaption("Механик ФИО");
			grid.addColumn(OrdersWithFio::getDateCreat).setId("dateCreat").setCaption("Дата создания заявки");
			grid.addColumn(OrdersWithFio::getCompletionDate).setId("completionDate").setCaption("Дата окончания работ");
			grid.addColumn(OrdersWithFio::getPrice).setId("price").setCaption("Цена");
			grid.addColumn(OrdersWithFio::getStatus).setId("status").setCaption("Статус");
			grid.setColumnOrder("id", "description", "clientFio", "mechanicFio", "status", "dateCreat", "completionDate",
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
