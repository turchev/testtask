package com.haulmont.testtask.view;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ErrorView extends VerticalLayout implements View{
	public ErrorView() {
		this.setMargin(true);
//		this.setSizeFull();
		Label lblEroor = new Label("Ошибка 404", ContentMode.PREFORMATTED);			
//		lblEroor.setSizeFull();	
		lblEroor.addStyleName("my-custom-style");
	
		this.addComponent(lblEroor);		
	}
}
