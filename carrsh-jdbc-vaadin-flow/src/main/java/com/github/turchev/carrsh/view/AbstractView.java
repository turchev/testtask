//package com.github.turchev.carrsh.view;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//
//@SuppressWarnings("serial")
//public abstract class AbstractView extends VerticalLayout {	
//	
//	HorizontalLayout groupButtons;	
//	
//	protected AbstractView() {		
//		this.setMargin(true);	
//		this.addComponent(getLowerGroupButtons());
//	}
//	
//	private HorizontalLayout getLowerGroupButtons() {
//		groupButtons = new HorizontalLayout();
//		Button btnAdd = new Button("Добавить");
//		Button btnChange = new Button("Изменить");
//		Button btnDelete = new Button("Удалить");
//		btnAdd.addClickListener(event -> {		
//			btnAddClick();
//		});
//		btnChange.addClickListener(event -> {
//			btnChangeClick();
//		});
//		btnDelete.addClickListener(event -> {
//			btnDeleteClick();
//		});
//		groupButtons.addComponent(btnAdd);
//		groupButtons.addComponent(btnChange);
//		groupButtons.addComponent(btnDelete);
//		return groupButtons;
//	}
//	
//	protected abstract void btnAddClick();
//	
//	protected abstract void btnChangeClick();
//	
//	protected abstract void btnDeleteClick();
//}
