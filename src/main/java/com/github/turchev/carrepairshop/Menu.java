package com.github.turchev.carrepairshop;

import com.github.turchev.carrepairshop.view.client.ClientView;
import com.github.turchev.carrepairshop.view.mechanic.MechanicView;
import com.github.turchev.carrepairshop.view.orders.OrdersView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;

public class Menu extends VerticalLayout {

    private static final long serialVersionUID = 1946084039439880360L;
    private final Tabs tabs = new Tabs();

    public Menu() {
        setClassName("menu-bar");

        Button btnOrders = new Button("Заказы");
        btnOrders.addClickListener(e -> btnOrders.getUI().ifPresent(ui -> ui.navigate(OrdersView.NAME)));
        btnOrders.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_PRIMARY);

        Button btnClient = new Button("Клиент");
        btnClient.addClickListener(e -> btnClient.getUI().ifPresent(ui -> ui.navigate(ClientView.NAME)));
        btnClient.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_PRIMARY);

        Button btnMechanic = new Button("Механик");
        btnMechanic.addClickListener(e -> btnMechanic.getUI().ifPresent(ui -> ui.navigate(MechanicView.NAME)));
        btnMechanic.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_PRIMARY);

        add(btnOrders, btnClient, btnMechanic);
        this.setSizeUndefined();
        UI.getCurrent().navigate(OrdersView.NAME);
    }

}
