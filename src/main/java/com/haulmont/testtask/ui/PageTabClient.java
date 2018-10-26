package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Client;
import com.vaadin.ui.Grid;

public class PageTabClient extends PageTabAbstract {	
	
	public PageTabClient() {
		grid = new Grid<>(Client.class);	
	}

}
