package com.github.turchev.carrepairshop.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractView extends VerticalLayout {
	
	HorizontalLayout groupButtons;	
	
	protected AbstractView() {		
		setMargin(true);	
		add(getLowerGroupButtons());
	}
	
	private HorizontalLayout getLowerGroupButtons() {
		groupButtons = new HorizontalLayout();
		Button btnAdd = new Button("Добавить");
		Button btnChange = new Button("Изменить");
		Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> btnAddClick());
		btnChange.addClickListener(event -> btnChangeClick());
		btnDelete.addClickListener(event -> btnDeleteClick());
		groupButtons.add(btnAdd);
		groupButtons.add(btnChange);
		groupButtons.add(btnDelete);
		return groupButtons;
	}
	
	protected abstract void btnAddClick();
	
	protected abstract void btnChangeClick();
	
	protected abstract void btnDeleteClick();
}
