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

@objid ("40d417dd-2516-4326-bc6b-adcbacf0f0a1")
public class FormHelper {
    @objid ("26502c8f-2567-4a8d-8f92-682c5fdf9180")
    private HorizontalLayout groupButtons;

    @objid ("b937aa31-3a89-46c4-9e54-8875db8c10a3")
    private VerticalLayout webPage;

    @objid ("83304016-b8bc-4302-8fb4-c41786566bad")
    @SuppressWarnings("rawtypes")
    private Grid grid;

    @objid ("8d5c9f2d-18f6-4cde-a6d0-62a0849288b9")
    private HorizontalLayout filterPanel;

    @objid ("2a90c6be-062d-4aee-b576-cc35b3370f25")
    public FormHelper() {
    }

    @objid ("e371fb55-0d57-41fc-9fee-10d5d1379a73")
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

    @objid ("e6106c47-ec43-4fa8-aaa7-595bd1f1fc0e")
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

    @objid ("bdff2131-db96-489e-a661-9664ab4a2a62")
    private HorizontalLayout getFilterPanel() {
        TextField description = new TextField("Описание");
        TextField status = new TextField("Статус");
        TextField client = new TextField("Клиент");
        
        filterPanel = new HorizontalLayout(description, status, client);
        return filterPanel;
    }

}
