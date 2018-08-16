package com.haulmont.testtask.ui;

import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DataEditorOrder extends Window implements SaveAndEdit {

	public DataEditorOrder() {
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public void save() {
		// TODO Сохранение данных в таблицу order
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
