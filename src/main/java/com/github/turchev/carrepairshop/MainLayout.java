package com.github.turchev.carrepairshop;

import com.github.turchev.carrepairshop.view.client.ClientView;
import com.github.turchev.carrepairshop.view.mechanic.MechanicView;
import com.github.turchev.carrepairshop.view.orders.OrdersView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Car repair shop", shortName = "CarRSh")
@Route("")
@PageTitle("CarRSh")
public class MainLayout extends FlexLayout implements RouterLayout {
	private static final long serialVersionUID = -8202252203294960997L;

	public MainLayout() {
		setSizeFull();
		setClassName("main-layout");
		Menu menu = new Menu();
        menu.addView(OrdersView.class, OrdersView.NAME, VaadinIcon.INFO_CIRCLE.create());
        menu.addView(MechanicView.class, MechanicView.NAME, VaadinIcon.EDIT.create());
        menu.addView(ClientView.class, ClientView.NAME, VaadinIcon.INFO_CIRCLE.create());
		add(menu);				
	}
}
