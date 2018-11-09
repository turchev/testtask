package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.Mechanic;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;

@objid ("6f563035-e079-4efc-800b-567431317a9a")
public class PageTabMechanic extends PageTabAbstract {
    @objid ("9fd1ea8c-0d11-4f9d-86be-0d9024237ffb")
    public PageTabMechanic() {
        grid = new Grid<>(Mechanic.class);
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
