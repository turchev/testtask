package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.EnumEntity;
import com.haulmont.testtask.data.dataSets.ClientDataSet;
import com.haulmont.testtask.data.dataSets.MechanicDataSet;
import com.haulmont.testtask.data.dataSets.OrderDataSet;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Вспомогательный временный класс-набросок,
 * для отладки UI каркаса
 */


public class FormHelper {
	
	private HorizontalLayout groupButtons;
	private VerticalLayout webPage;
	@SuppressWarnings("rawtypes")
	private Grid grid;
	private HorizontalLayout filterPanel;
	
	public FormHelper() {
	}
	
	/**
	 * Возвращает VerticalLayout для формирования экранной формы "Клиенты, Механики, Заказы" 	 
	 */
	public VerticalLayout getPage(EnumEntity entityType) {
		webPage = new VerticalLayout();
		
		switch (entityType) {
		case CLIENT:			
			grid = new Grid<>(ClientDataSet.class);					
			break;
		case MECHANIC:			
			grid = new Grid<>(MechanicDataSet.class);			
			break;
		case ORDER:			
			webPage.addComponent(getFilterPanel());
			grid = new Grid<>(OrderDataSet.class);			
			break;
		default:
			return null;
		}

		grid.setWidth(100.0f, Unit.PERCENTAGE);
		webPage.setMargin(true);
		webPage.addComponent(grid);
		webPage.addComponent(getLowerGroupButtons());		
		
		return webPage;		
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
	
	private HorizontalLayout getFilterPanel() {
				
		TextField description = new TextField("Описание");
		TextField status = new TextField("Статус");
		TextField client = new TextField("Клиент");

		filterPanel = new HorizontalLayout(description, status, client);
		
		return filterPanel;	
	}


}
