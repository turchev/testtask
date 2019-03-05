package com.haulmont.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.view.ClientView;
import com.haulmont.testtask.view.ErrorView;
import com.haulmont.testtask.view.MechanicView;
import com.haulmont.testtask.view.OrdersView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected void init(VaadinRequest vaadinRequest) {		
		try {
			Button btnOrders = new Button("Заказы", e -> getNavigator().navigateTo(OrdersView.NAME));
			btnOrders.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);

			Button btnClient = new Button("Клиент", e -> getNavigator().navigateTo(ClientView.NAME));
			btnClient.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);
			Button btnMechanic = new Button("Механик", e -> getNavigator().navigateTo(MechanicView.NAME));
			btnMechanic.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);

			CssLayout menu = new CssLayout(btnOrders, btnClient, btnMechanic);
			menu.addStyleName(ValoTheme.MENU_ROOT);
			menu.setSizeUndefined();

			CssLayout viewContainer = new CssLayout();
			viewContainer.setSizeFull();

			HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
			mainLayout.setExpandRatio(viewContainer, 1.0f);
			mainLayout.setSizeFull();
			setContent(mainLayout);

			Navigator navigator = new Navigator(this, viewContainer);
			navigator.addView(OrdersView.NAME, OrdersView.class);
			navigator.addView(ClientView.NAME, ClientView.class);
			navigator.addView(MechanicView.NAME, MechanicView.class);
			navigator.addView(ErrorView.NAME, ErrorView.class);
			navigator.setErrorView(ErrorView.class);
			getNavigator().navigateTo(OrdersView.NAME);
			LOG.debug("Vaadin Interface completed successfully");

		} catch (Exception e) {
			LOG.error("Starting the Vaadin Interface failed: {}", e);
			getNavigator().navigateTo(ErrorView.NAME);
		}
	}
}
