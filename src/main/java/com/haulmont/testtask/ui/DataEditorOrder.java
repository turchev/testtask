package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("b01e7082-c534-49b7-91e0-8510d21147c9")
@SuppressWarnings("serial")
public class DataEditorOrder extends Window implements SaveAndEdit {
    @objid ("8f2ae4a3-b14e-4dc2-ad2c-c171d25266fd")
    public DataEditorOrder() {
        // TODO Auto-generated constructor stub
    }

    @objid ("6f48b5c6-ad67-4715-bbe0-907a32ae61b2")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу order
        update();
    }

    @objid ("1888a3a8-cf49-4e52-8089-d12cf880f891")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("80516424-5e7b-4f55-8e24-826d88bbaa77")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
