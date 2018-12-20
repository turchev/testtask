package com.haulmont.testtask.view;

import com.haulmont.testtask.entity.Mechanic;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;


@SuppressWarnings("serial")
public class MechanicView extends AbstractView implements View {
    
    public MechanicView() {
    	Grid<Mechanic> grid = new Grid<>(Mechanic.class);
        grid.setWidth(100.0f, Unit.PERCENTAGE);        
        this.addComponent(grid);
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
	
	@Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to Mechanic View");
    }

}
