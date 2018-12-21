package com.haulmont.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.view.ClientView;
import com.haulmont.testtask.view.MechanicView;
import com.haulmont.testtask.view.OrderView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.navigator.View;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	private static final Logger LOG = LogManager.getLogger();
	Navigator navigator;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout verticalLayot = new VerticalLayout();
		// Create a navigator to control the views
		navigator = new Navigator(UI.getCurrent(), verticalLayot);
		new View(verticalLayot);
		final String viewClassName = view.getClass().getSimpleName();
		navigator.addView(viewClassName, view);
		menu.addItem(viewClassName, selectedItem -> navigator.navigateTo(viewClassName));

		try {
			TabSheet tabSheet = new TabSheet();
			tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
			tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

			TabSheet.Tab tab1 = tabSheet.addTab(new ClientView());
			tab1.setCaption("Клиенты");
			tab1.setDescription("Список клиентов");

			TabSheet.Tab tab2 = tabSheet.addTab(new MechanicView());
			tab2.setCaption("Механики");
			tab2.setDescription("Список механиков");

			TabSheet.Tab tab3 = tabSheet.addTab(new OrderView());
			tab3.setCaption("Заказы");
			tab3.setDescription("Список заказов");
			verticalLayot.addComponent(tabSheet);

		} catch (Exception e) {
			LOG.error(e);
		}

		setContent(verticalLayot);
	}
}
