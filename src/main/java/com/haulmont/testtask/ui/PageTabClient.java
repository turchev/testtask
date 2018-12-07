package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.Client;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;


public class PageTabClient extends PageTabAbstract {
    
    public PageTabClient() {
        grid = new Grid<>(Client.class);           
        grid.setWidth(100.0f, Unit.PERCENTAGE);        
        webPage.addComponent(grid);
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
