package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@objid("ed8c22e1-82b0-455d-993b-0187743ba0c4")
public abstract class PageTabAbstract implements PageTab {

	VerticalLayout webPage = null;
	HorizontalLayout groupButtons = null;
	Grid<?> grid = null;

	PageTabAbstract() {
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

	private HorizontalLayout getLowerGroupButtons() {
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
		return groupButtons;
	}

	@Override
	public VerticalLayout getPageTab() {
		grid.setWidth(100.0f, Unit.PERCENTAGE);
		webPage.addComponent(grid);
		return webPage;		
	}

}
