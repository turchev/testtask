package com.github.turchev.carrsh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.turchev.carrsh.view.client.ClientView;
import com.github.turchev.carrsh.view.mechanic.MechanicView;
import com.github.turchev.carrsh.view.orders.OrdersView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@SuppressWarnings("serial")
public class MainUI extends Div implements RouterLayout {
	private static final Logger LOG = LogManager.getLogger();

	public MainUI() {
		Button btnOrders = new Button("Заказы", e -> getNavigator().navigateTo(OrdersView.NAME));
		btnOrders.getElement().setAttribute("theme", "contrast primary");

		Button btnClient = new Button("Клиент", e -> getNavigator().navigateTo(ClientView.NAME));
		btnClient.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);

		Button btnMechanic = new Button("Механик", e -> getNavigator().navigateTo(MechanicView.NAME));
		btnMechanic.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_ERROR);
	}

	Div menu = new Div(btnOrders, btnClient, btnMechanic);
//			menu.addStyleName(Theme.class);
//			menu.setSizeUndefined();

	Div viewContainer = new Div();viewContainer.setSizeFull();

	HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
//			mainLayout.setExpandRatio(viewContainer, 1.0f);
	mainLayout.setSizeFull();

	add(mainLayout);

//			Navigator navigator = new Navigator(this, viewContainer);
//			navigator.addView(OrdersView.NAME, OrdersView.class);
//			navigator.addView(ClientView.NAME, ClientView.class);
//			navigator.addView(MechanicView.NAME, MechanicView.class);
//			navigator.addView(ErrorView.NAME, ErrorView.class);
//			navigator.setErrorView(ErrorView.class);
//			getNavigator().navigateTo(OrdersView.NAME);
			LOG.debug("Vaadin Interface completed successfully");

		}catch(

	Exception e)
	{
		LOG.error("Starting the Vaadin Interface failed: {}", e);
//			getNavigator().navigateTo(ErrorView.NAME);

	}
}
