package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Client;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;

@objid ("42cad638-18c5-4c58-9c7c-71a4d3d76ca6")
public class PageTabClient extends PageTabAbstract {
    @objid ("ceb573be-2761-40fa-a7e1-db34fbf41a1b")
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
