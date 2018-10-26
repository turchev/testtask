package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Order;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class PageTabOrder extends PageTabAbstract {
	
	public PageTabOrder() {
		webPage.addComponent(getFilterPanel());
        grid = new Grid<>(Order.class);    
	}
	
	private HorizontalLayout getFilterPanel() {
		
        TextField description = new TextField("Описание");
        TextField status = new TextField("Статус");
        TextField client = new TextField("Клиент");
        
        HorizontalLayout filterPanel = new HorizontalLayout(description, status, client);
        return filterPanel;
    }

}
