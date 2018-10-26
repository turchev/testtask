package com.haulmont.testtask.ui;

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

    @objid ("268d20d0-5fd4-46f3-8c41-a20f75982db3")
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainLayot = new MainLayot();
        setContent(mainLayot);
    }

}
