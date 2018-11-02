package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Order;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@objid ("269fdf60-641b-4bce-82e4-c8d38b8f912f")
public class PageTabOrder extends PageTabAbstract {
	
	private HorizontalLayout filterPanel;
	
    @objid ("5a9294f7-5bf0-4d54-a59d-d365255ed70b")
    public PageTabOrder() {
    	TextField description = new TextField("Описание");
        TextField status = new TextField("Статус");
        TextField client = new TextField("Клиент");
        filterPanel = new HorizontalLayout(description, status, client);
        webPage.addComponent(filterPanel);
        grid = new Grid<>(Order.class);        
        grid.setWidth(100.0f, Unit.PERCENTAGE);        
        webPage.addComponent(grid);
    }

    @objid ("6341a673-92f1-4584-b7d5-2bd895d6ff54")
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
