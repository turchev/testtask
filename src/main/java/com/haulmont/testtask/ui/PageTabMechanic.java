package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Mechanic;
import com.vaadin.ui.Grid;

public class PageTabMechanic extends PageTabAbstract {

	public PageTabMechanic() {
		grid = new Grid<>(Mechanic.class);		
	}
}
