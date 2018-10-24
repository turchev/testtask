package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.Client;
import com.haulmont.testtask.data.entity.EntityType;
import com.haulmont.testtask.data.entity.Mechanic;
import com.haulmont.testtask.data.entity.Order;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@objid ("b79d9210-963d-4ac0-aef4-dcf0f3f5f659")
public class FormHelper {
    @objid ("9c03661a-a967-42fa-a9c8-86d79f9f32ba")
    private HorizontalLayout groupButtons;

    @objid ("596302b5-64f3-4ab8-b246-32b57874372b")
    private VerticalLayout webPage;

    @objid ("923d3415-9ce2-4faf-9f78-ed4bf2bf1037")
    @SuppressWarnings("rawtypes")
    private Grid grid;

    @objid ("1f8380b8-e8e7-47cf-b3dd-f5f42bcc9a87")
    private HorizontalLayout filterPanel;

    @objid ("8eb41e6f-4e3b-4a81-a207-cfd5abba511a")
    public FormHelper() {
    }

    @objid ("702f426f-f43c-4e85-9e72-76d548bcd908")
    public VerticalLayout getPage(EntityType entityType) {
        webPage = new VerticalLayout();
        
        switch (entityType) {
        case CLIENT:            
            grid = new Grid<>(Client.class);                    
            break;
        case MECHANIC:            
            grid = new Grid<>(Mechanic.class);            
            break;
        case ORDER:            
            webPage.addComponent(getFilterPanel());
            grid = new Grid<>(Order.class);            
            break;
        default:
            return null;
        }
        
        grid.setWidth(100.0f, Unit.PERCENTAGE);
        webPage.setMargin(true);
        webPage.addComponent(grid);
        webPage.addComponent(getLowerGroupButtons());
        return webPage;
    }

    @objid ("1e0b8ccf-f06d-46fe-a5ac-41e31cc01c49")
    private HorizontalLayout getLowerGroupButtons() {
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
        return groupButtons;
    }

    @objid ("849f8152-b763-4b91-a582-e1388e759eaf")
    private HorizontalLayout getFilterPanel() {
        TextField description = new TextField("Описание");
        TextField status = new TextField("Статус");
        TextField client = new TextField("Клиент");
        
        filterPanel = new HorizontalLayout(description, status, client);
        return filterPanel;
    }

}
