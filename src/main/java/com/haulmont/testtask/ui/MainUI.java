package com.haulmont.testtask.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.haulmont.testtask.data.EnumEntity;
import com.haulmont.testtask.data.dbService.DBConnection;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	private final FormHelper helper = new FormHelper();
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {		
		
		Connection con = DBConnection.getConnection();
		System.out.println("Connection Test");
		
		
		final VerticalLayout layoutMain = new VerticalLayout();
//		layoutMain.setSizeFull();
		layoutMain.setMargin(true);
		final TabSheet tabsheet = new TabSheet(); 
//		tabsheet.setSizeFull();
//		tabsheet.setHeight(100.0f, Unit.PERCENTAGE);
		tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
				
		TabSheet.Tab tab1 = tabsheet.addTab(helper.getPage(EnumEntity.CLIENT));		
		tab1.setCaption("Клиенты");
		tab1.setDescription("Список клиентов");
				
		TabSheet.Tab tab2 = tabsheet.addTab(helper.getPage(EnumEntity.MECHANIC));
		tab2.setCaption("Механики");
		tab2.setDescription("Список механиков");
		
		TabSheet.Tab tab3 = tabsheet.addTab(helper.getPage(EnumEntity.ORDER));
		tab3.setCaption("Заказы");
		tab3.setDescription("Список заказов");
				        
		layoutMain.addComponent(tabsheet);		

		setContent(layoutMain);

	}

}

