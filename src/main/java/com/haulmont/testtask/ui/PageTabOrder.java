package com.haulmont.testtask.ui;

import com.haulmont.testtask.entity.Orders;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;


public class PageTabOrder extends PageTabAbstract {
	
	private HorizontalLayout filterPanel;
	
    
    public PageTabOrder() {
    	TextField description = new TextField("Описание");
        TextField status = new TextField("Статус");
        TextField client = new TextField("Клиент");
        filterPanel = new HorizontalLayout(description, status, client);
        webPage.addComponent(filterPanel);
        grid = new Grid<>(Orders.class);        
        grid.setWidth(100.0f, Unit.PERCENTAGE);        
        webPage.addComponent(grid);
    }

    
    private HorizontalLayout getFilterPanel() { 	
        return filterPanel;
    }

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

}
