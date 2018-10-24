package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("a5507e59-6cfe-474b-b67f-79d15a3364d8")
@SuppressWarnings("serial")
public class DataEditorClient extends Window implements SaveAndEdit {
//    private VerticalLayout verticalLayout;
//    final Button open = new Button("Окрыть модальное окно");
//
//    public DataEditorClient() {
//        super("Subs on Sale"); // Set window caption
//        setModal(true);
//        center();
//        setClosable(false);
//        setContent(new Button("Close me", event -> close()));
//
//        open.addClickListener(event -> {
//            // Create a sub-window and set the content
//            setCaption("Sub-window");
//
//            // Put some components in it
//            verticalLayout.addComponent(new Label("Meatball sub"));
//            verticalLayout.addComponent(new Button("Awlright"));
//            // Center it in the browser window
//            center();
//            setContent(verticalLayout);
//        });
//        // TODO Auto-generated constructor stub
//    }
    @objid ("52d18e8f-19c7-42ae-b397-c86dbf5cbb31")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу client
        update();
    }

    @objid ("2076e65d-907c-4e81-a770-063b92243122")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("9924cf06-60da-4e0a-a5fc-a066f2c7d644")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
