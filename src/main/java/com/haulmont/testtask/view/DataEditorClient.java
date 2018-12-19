package com.haulmont.testtask.view;

import com.vaadin.ui.Window;

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
