package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("cd11c14b-606e-4ebd-8509-503a30b9230e")
@SuppressWarnings("serial")
public class DataEditorMechanic extends Window implements SaveAndEdit {
    @objid ("8ecca5e4-95b9-4cd8-9ba6-bf09ebb99dba")
    public DataEditorMechanic() {
        // TODO Auto-generated constructor stub
    }

    @objid ("6621d960-9eb3-49b8-9c95-654cbaf9eeca")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу mechanic
        update();
    }

    @objid ("a8fe65e8-c3f3-4e96-a11c-ec0307083421")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("385b6bd2-5a5a-4895-a7b8-f2ef134da66a")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
