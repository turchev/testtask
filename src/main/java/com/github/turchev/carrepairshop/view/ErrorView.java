package com.github.turchev.carrepairshop.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class ErrorView extends VerticalLayout implements View {
	public static final String NAME = "error";

	public ErrorView() {
		this.setSizeFull();	
		this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Label lblEroor = new Label("ERROR");		
		lblEroor.addStyleName("errorLabel");	
		this.addComponent(lblEroor);
	}
}
