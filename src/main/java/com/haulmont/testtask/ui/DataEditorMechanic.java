package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("d18c81ef-a9df-46c1-9107-b905e426f126")
@SuppressWarnings("serial")
public class DataEditorMechanic extends Window implements SaveAndEdit {
    @objid ("70c8b765-0c2c-4ebd-a3b7-dd3f1e2e38a5")
    public DataEditorMechanic() {
        // TODO Auto-generated constructor stub
    }

    @objid ("5519c5e6-38ab-401c-afb6-050ecc185f6d")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу mechanic
        update();
    }

    @objid ("5d02fbd2-f631-433c-ac1d-27bc56a00b24")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("81d0a6c8-641d-48d3-b050-5b2aef23c7b5")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
