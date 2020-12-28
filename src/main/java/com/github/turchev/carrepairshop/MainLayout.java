package com.github.turchev.carrepairshop;

import com.github.turchev.carrepairshop.view.client.ClientView;
import com.github.turchev.carrepairshop.view.mechanic.MechanicView;
import com.github.turchev.carrepairshop.view.orders.OrdersView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

@PWA(
		name = "Car repair shop",
		shortName = "CarRSh",
		offlineResources = {
				"./styles/offline.css",
				"./images/offline.png"
		},
		enableInstallPrompt = false
)

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {
	public MainLayout() {
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("Car repair shop");
		logo.addClassName("logo");
		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
		header.setDefaultVerticalComponentAlignment(
				FlexComponent.Alignment.CENTER);
		header.setWidth("100%");
		header.addClassName("header");
		addToNavbar(header);
	}

	private void createDrawer() {
		RouterLink listLink = new RouterLink(OrdersView.NAME, OrdersView.class);
		listLink.setHighlightCondition(HighlightConditions.sameLocation());

		addToDrawer(new VerticalLayout(
				listLink,
				new RouterLink(MechanicView.NAME, MechanicView.class),
				new RouterLink(ClientView.NAME, ClientView.class)
		));
	}
}

