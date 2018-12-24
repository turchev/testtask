package com.haulmont.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.view.ClientView;
import com.haulmont.testtask.view.MechanicView;
import com.haulmont.testtask.view.OrderView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	private static final Logger LOG = LogManager.getLogger();
	
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout verticalLayot = new VerticalLayout();			
		
//		final String viewClassName = verticalLayot.getClass().getSimpleName();
		
//		menu.addItem(viewClassName, selectedItem -> navigator.navigateTo(viewClassName));

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
			
			setContent(verticalLayot);
			Navigator navigator = new Navigator(UI.getCurrent(), verticalLayot);		
			navigator.addView("tab1", (View) tab1);
			navigator.addView("tab2", (View) tab2);
			navigator.addView("tab3", (View) tab3);

		} catch (Exception e) {
			LOG.error(e);
		}		
	}
}
