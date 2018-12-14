package com.haulmont.testtask.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public abstract class PageTabFactory implements PageTab{
	
	VerticalLayout webPage;
	HorizontalLayout groupButtons;
	Grid<?> grid = null;
	
	protected PageTabFactory() {
		webPage = new VerticalLayout();
		webPage.setMargin(true);
		groupButtons = new HorizontalLayout();
		final Button btnAdd = new Button("Добавить");
		final Button btnChange = new Button("Изменить");
		final Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> {
			Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
		});
		btnChange.addClickListener(event -> {
			Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
		});
		btnDelete.addClickListener(event -> {
			Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
		});
		groupButtons.addComponent(btnAdd);
		groupButtons.addComponent(btnChange);
		groupButtons.addComponent(btnDelete);
		webPage.addComponent(getLowerGroupButtons());
	}
	
	public static PageTabFactory  getFactory(PageTabType type) throws UiException {
		try {
			switch (type) {
			case CLIENT:
				return new PageTabClient();
			case MECHANIC:
				return new PageTabMechanic();
			case ORDER:
				return new PageTabOrder();
			default:
				throw new IllegalArgumentException(type.toString());
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
	}	
	
	private HorizontalLayout getLowerGroupButtons() {
		groupButtons = new HorizontalLayout();
		final Button btnAdd = new Button("Добавить");
		final Button btnChange = new Button("Изменить");
		final Button btnDelete = new Button("Удалить");
		btnAdd.addClickListener(event -> {
			Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
			btnAddClick();
		});
		btnChange.addClickListener(event -> {
			Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
			btnChangeClick();
		});
		btnDelete.addClickListener(event -> {
			Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
			btnDeleteClick();
		});
		groupButtons.addComponent(btnAdd);
		groupButtons.addComponent(btnChange);
		groupButtons.addComponent(btnDelete);
		return groupButtons;
	}
	
	@Override
	public VerticalLayout getPageTab() {
		return webPage;
	}
	
	abstract void btnAddClick();

	abstract void btnChangeClick();

	abstract void btnDeleteClick();
}
