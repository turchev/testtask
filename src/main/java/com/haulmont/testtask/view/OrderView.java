package com.haulmont.testtask.view;

import com.haulmont.testtask.entity.Orders;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class OrderView extends AbstractView implements View {

	private HorizontalLayout filterPanel;

	public OrderView() {
		TextField description = new TextField("Описание");
		TextField status = new TextField("Статус");
		TextField client = new TextField("Клиент");
		filterPanel = new HorizontalLayout(description, status, client);
		this.addComponent(filterPanel);
		Grid<Orders> grid = new Grid<>(Orders.class);
		grid.setWidth(100.0f, Unit.PERCENTAGE);
		this.addComponent(grid);
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
