package com.haulmont.testtask.view;

import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DataEditorClient extends Window implements SaveAndEdit {

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
