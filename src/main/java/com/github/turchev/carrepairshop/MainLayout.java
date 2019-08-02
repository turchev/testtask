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
		
//		CssLayout viewContainer = new CssLayout();
//		viewContainer.setSizeFull();
//
//		HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
//		mainLayout.setExpandRatio(viewContainer, 1.0f);
//		mainLayout.setSizeFull();
//		setContent(mainLayout);
//
//		Navigator navigator = new Navigator(this, viewContainer);
//		navigator.addView(OrdersView.NAME, OrdersView.class);
//		navigator.addView(ClientView.NAME, ClientView.class);
//		navigator.addView(MechanicView.NAME, MechanicView.class);
//		navigator.addView(ErrorView.NAME, ErrorView.class);
//		navigator.setErrorView(ErrorView.class);
//		getNavigator().navigateTo(OrdersView.NAME);		
		
	}
}
