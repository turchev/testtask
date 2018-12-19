package com.haulmont.testtask.view;


import com.vaadin.ui.Window;


@SuppressWarnings("serial")
public class DataEditorMechanic extends Window implements SaveAndEdit {
    
    public DataEditorMechanic() {
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу mechanic
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
