package com.haulmont.testtask.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {		
				
		MainLayot mainLayot = new MainLayot();
		setContent(mainLayot);
	}
	
}

