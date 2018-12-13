package com.haulmont.testtask.ui;

import com.haulmont.testtask.dao.DaoException;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class MainLayot extends VerticalLayout {
	
	private TabSheet tabSheet;
	
	public MainLayot() throws DaoException {
        tabSheet = new TabSheet();  
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
		
		this.addComponent(tabSheet);
	}
}
