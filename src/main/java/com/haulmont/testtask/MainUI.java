package com.haulmont.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haulmont.testtask.view.ClientView;
import com.haulmont.testtask.view.MechanicView;
import com.haulmont.testtask.view.OrderView;
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
		
		Button btnClient = new Button("Клиент", e -> getNavigator().navigateTo("Client"));
		btnClient.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);
		Button btnMechanic = new Button("Механик", e -> getNavigator().navigateTo("Mechanic"));
		btnMechanic.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);
		Button btnOrders = new Button("Заказы", e -> getNavigator().navigateTo("Orders"));
		btnOrders.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_TITLE);

		CssLayout menu = new CssLayout(btnClient, btnMechanic, btnOrders);
		menu.addStyleName(ValoTheme.MENU_ROOT);
		menu.setSizeUndefined();

		CssLayout viewContainer = new CssLayout();
		viewContainer.setSizeFull();		
//		viewContainer.setSizeUndefined();

		HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
		mainLayout.setExpandRatio(viewContainer, 1.0f);
//		mainLayout.setComponentAlignment(viewContainer, Alignment.TOP_LEFT);
		mainLayout.setSizeFull();
		setContent(mainLayout);

		Navigator navigator = new Navigator(this, viewContainer);
		navigator.addView("Client", ClientView.class);
		navigator.addView("Mechanic", MechanicView.class);
		navigator.addView("Orders", OrderView.class);		
	}

//	@Override
//	protected void init(VaadinRequest vaadinRequest) {
//
//		VerticalLayout verticalLayot = new VerticalLayout();
//
////		final String viewClassName = verticalLayot.getClass().getSimpleName();
//
////		menu.addItem(viewClassName, selectedItem -> navigator.navigateTo(viewClassName));
//
//		try {
//			TabSheet tabSheet = new TabSheet();
//			tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
//			tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
//
//			TabSheet.Tab tab1 = tabSheet.addTab(new ClientView());
//			tab1.setCaption("Клиенты");
//			tab1.setDescription("Список клиентов");
//
//			TabSheet.Tab tab2 = tabSheet.addTab(new MechanicView());
//			tab2.setCaption("Механики");
//			tab2.setDescription("Список механиков");
//
//			TabSheet.Tab tab3 = tabSheet.addTab(new OrderView());
//			tab3.setCaption("Заказы");
//			tab3.setDescription("Список заказов");
//			verticalLayot.addComponent(tabSheet);
//
//			setContent(verticalLayot);
//			Navigator navigator = new Navigator(UI.getCurrent(), new ViewDisplay() {
//				@Override
//				public void showView(View view) {					
//				}
//			});
//						
//			navigator.addView("tab1", (View) tab1);
//			navigator.addView("tab2", (View) tab2);
//			navigator.addView("tab3", (View) tab3);
//
//		} catch (Exception e) {
//			LOG.error(e);
//		}
//	}
}
