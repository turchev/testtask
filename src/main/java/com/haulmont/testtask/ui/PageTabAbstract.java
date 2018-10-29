package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@objid ("ed8c22e1-82b0-455d-993b-0187743ba0c4")
public abstract class PageTabAbstract implements PageTab {
    @objid ("8709cceb-1bba-466b-8902-0c58a13e9462")
     VerticalLayout webPage = null;

    @objid ("a83ac287-5ecc-4067-a5f4-6174034dc862")
     HorizontalLayout groupButtons = null;

    @objid ("2dbe00bf-167d-42ad-bc86-412ea063f2ad")
     Grid<?> grid = null;

    @objid ("8b56a745-c6e2-44ce-8f6e-403ef78b1b57")
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

    @objid ("e0a69cf8-688f-4be6-a572-81ef05e2ed76")
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

    @objid ("adcaa9ac-180a-4ed9-b422-8a9a6bc32c9e")
    @Override
    public VerticalLayout getPageTab() {
        return webPage;
    }
    
    abstract void btnAddClick();
    abstract void btnChangeClick();
    abstract void btnDeleteClick();

}
