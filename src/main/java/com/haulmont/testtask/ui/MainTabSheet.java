package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;

@objid("5f6e7d5b-7a5f-45a3-9ad0-92dbaf769534")
@SuppressWarnings("serial")
public class MainTabSheet extends TabSheet {
	
	@objid("92195e46-bff7-4af5-8889-f72aa0141598")
	public MainTabSheet() {
		
		this.addStyleName(ValoTheme.TABSHEET_FRAMED);
		this.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		
		TabSheet.Tab tab1 = this.addTab(new PageTabClient().getPageTab());
		tab1.setCaption("Клиенты");
		tab1.setDescription("Список клиентов");

		TabSheet.Tab tab2 = this.addTab(new PageTabMechanic().getPageTab());
		tab2.setCaption("Механики");
		tab2.setDescription("Список механиков");

		TabSheet.Tab tab3 = this.addTab(new PageTabOrder().getPageTab());
		tab3.setCaption("Заказы");
		tab3.setDescription("Список заказов");

	}

}
