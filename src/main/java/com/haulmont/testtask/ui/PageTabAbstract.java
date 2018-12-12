package com.haulmont.testtask.ui;


import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;


public abstract class PageTabAbstract implements PageTab {
    
     VerticalLayout webPage = null;    
     HorizontalLayout groupButtons = null;    
     Grid<?> grid = null;

    
    PageTabAbstract() {
        webPage = new VerticalLayout();
        webPage.setMargin(true);       
        groupButtons = new HorizontalLayout();
        final Button btnAdd = new Button("Добавить");
        final Button btnChange = new Button("Изменить");
        final Button btnDelete = new Button("Удалить");
        btnAdd.addClickListener(event -> {
            Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
        });
        btnChange.addClickListener(event -> {
            Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
        });
        btnDelete.addClickListener(event -> {
            Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
        });
        groupButtons.addComponent(btnAdd);
        groupButtons.addComponent(btnChange);
        groupButtons.addComponent(btnDelete);
        webPage.addComponent(getLowerGroupButtons());
    }

    
    private HorizontalLayout getLowerGroupButtons() {
        groupButtons = new HorizontalLayout();
        final Button btnAdd = new Button("Добавить");
        final Button btnChange = new Button("Изменить");
        final Button btnDelete = new Button("Удалить");
        btnAdd.addClickListener(event -> {
            Notification.show("TODO", "Добавить", Notification.Type.HUMANIZED_MESSAGE);
            btnAddClick();
        });
        btnChange.addClickListener(event -> {
            Notification.show("TODO", "Изменить", Notification.Type.HUMANIZED_MESSAGE);
            btnChangeClick();
        });
        btnDelete.addClickListener(event -> {
            Notification.show("TODO", "Удалить", Notification.Type.HUMANIZED_MESSAGE);
            btnDeleteClick();
        });
        groupButtons.addComponent(btnAdd);
        groupButtons.addComponent(btnChange);
        groupButtons.addComponent(btnDelete);
        return groupButtons;
    }

    
    @Override
    public VerticalLayout getPageTab() {
        return webPage;
    }
    
    abstract void btnAddClick();
    abstract void btnChangeClick();
    abstract void btnDeleteClick();

}
