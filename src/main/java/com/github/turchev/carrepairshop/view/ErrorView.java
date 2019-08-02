package com.github.turchev.carrepairshop.view;

import com.github.turchev.carrepairshop.MainLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
@Route(value = ErrorView.NAME, layout = MainLayout.class)
@PageTitle(ErrorView.NAME)
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
