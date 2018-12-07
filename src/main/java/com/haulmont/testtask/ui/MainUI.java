package com.haulmont.testtask.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
    
    private MainLayot mainLayot;
    private static final Logger log = Logger.getLogger(MainUI.class.getName());
	Date date = new Date();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'time' hh:mm:ss a zzz");
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
        mainLayot = new MainLayot();       
               
		log.log(Level.SEVERE, "{0}: Message level: SEVERE", formatForDateNow.format(date));
		log.log(Level.FINE, "{0}: Message level: SEVERE", formatForDateNow.format(date));
        setContent(mainLayot);
    }
}
