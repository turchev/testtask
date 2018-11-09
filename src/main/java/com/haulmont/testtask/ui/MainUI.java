package com.haulmont.testtask.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@objid ("8cf1e432-8924-4202-8292-034f5bb523e4")
@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
    @objid ("f590a5b5-86bd-4611-8444-11a2df26b793")
    private MainLayot mainLayot;
    private static final Logger log = Logger.getLogger(MainUI.class.getName());
	Date date = new Date();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'time' hh:mm:ss a zzz");

    @objid ("268d20d0-5fd4-46f3-8c41-a20f75982db3")
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
        mainLayot = new MainLayot();       
               
		log.log(Level.SEVERE, "{0}: Message level: SEVERE", formatForDateNow.format(date));
		log.log(Level.FINE, "{0}: Message level: SEVERE", formatForDateNow.format(date));
        setContent(mainLayot);
    }

}
