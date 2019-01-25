package com.haulmont.testtask.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public abstract class ClientWindowAbstract extends Window implements SaveAndEdit {	
	private Button btnApple;

	public ClientWindowAbstract() {
		init();		
	}
	
	private void init() {
		VerticalLayout vlLayout = new VerticalLayout();		
		vlLayout.addComponent(new Label("Test Label"));
		this.setWidth(400.0f, Unit.PIXELS);
		this.setModal(true);			
		btnApple = new Button("Применить");
		btnApple.addClickListener(event -> {
			btnAppleClick();
		});
		vlLayout.addComponent(btnApple);
		this.setContent(vlLayout);	
	}

	protected abstract void btnAppleClick();

	@Override
	public void save() {
		// TODO Сохранение данных в таблицу client
		update();
	}

	@Override
	public void cancel() {
		// TODO Отмена действий
	}

	@Override
	public void update() {
		// TODO Обновление отображаемых данных
	}

}
