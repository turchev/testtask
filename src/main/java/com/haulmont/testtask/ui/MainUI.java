package com.haulmont.testtask.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected void init(VaadinRequest vaadinRequest){		
		VerticalLayout verticalLayot = new VerticalLayout();
		try {
			TabSheet tabSheet = new TabSheet();
			tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
			tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

			TabSheet.Tab tab1 = tabSheet.addTab(new PageTabClient().getPageTab());
			tab1.setCaption("Клиенты");
			tab1.setDescription("Список клиентов");

			TabSheet.Tab tab2 = tabSheet.addTab(new PageTabMechanic().getPageTab());
			tab2.setCaption("Механики");
			tab2.setDescription("Список механиков");

			TabSheet.Tab tab3 = tabSheet.addTab(new PageTabOrder().getPageTab());
			tab3.setCaption("Заказы");
			tab3.setDescription("Список заказов");
			verticalLayot.addComponent(tabSheet);

		} catch (Exception e) {			
			LOG.error(e);		
		}

		setContent(verticalLayot);
	}
}
