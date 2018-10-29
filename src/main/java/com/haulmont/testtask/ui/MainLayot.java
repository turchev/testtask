package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@objid("bd055d5e-91c4-4c54-94ef-c07a56d1df3d")
@SuppressWarnings("serial")
public class MainLayot extends VerticalLayout {
	@objid("b4bf7c64-f60c-455e-ae37-85c4faf0cbd1")
	private TabSheet tabSheet;

	@objid("cecdeb32-c772-4fc3-ac8c-ddfd20d6ac32")
	public MainLayot() {
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
