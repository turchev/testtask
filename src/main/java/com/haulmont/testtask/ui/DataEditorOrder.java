package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("146b2db2-daba-4233-ac6e-70f271af661c")
@SuppressWarnings("serial")
public class DataEditorOrder extends Window implements SaveAndEdit {
    @objid ("f8c131d0-5db4-4a52-bec5-93ee127a90cd")
    public DataEditorOrder() {
        // TODO Auto-generated constructor stub
    }

    @objid ("18148023-ad42-4731-9b79-e5585fb22613")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу order
        update();
    }

    @objid ("6f11af18-8530-431c-a8e5-8d2762e91b00")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("1480e08b-815f-4498-9b0d-3478b175972a")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
