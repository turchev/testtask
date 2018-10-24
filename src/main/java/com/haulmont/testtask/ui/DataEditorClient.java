package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Window;

@objid ("d235389c-7682-4ab5-a588-6b3ec0864d70")
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
    @objid ("36644e54-0aaf-49a7-a614-7da533ad3d29")
    @Override
    public void save() {
        // TODO Сохранение данных в таблицу client
        update();
    }

    @objid ("53732d13-a197-46d8-92f5-dc6f003f7f0e")
    @Override
    public void cancel() {
        // TODO Отмена действий
    }

    @objid ("647485f2-47b9-4625-ad1b-696d38233ca9")
    @Override
    public void update() {
        // TODO Обновление отображаемых данных
    }

}
