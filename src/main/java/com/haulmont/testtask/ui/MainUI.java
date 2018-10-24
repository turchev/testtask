package com.haulmont.testtask.ui;

import java.util.ArrayList;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@objid ("03801564-e09c-45a1-a8cf-3d236c9aa35c")
@Theme("mytheme")
@SuppressWarnings("serial")
public class MainUI extends UI {
    @objid ("676730e5-fd9f-4d89-a225-872e983dca10")
    private ArrayList<String> aaa = new ArrayList<>();

    @objid ("5adc1b13-1a76-484e-b829-3615adee5145")
    private HashSet<String> eee = new HashSet<>();

    @objid ("c0affbcb-30bc-4d7f-a7a0-04b68b1af37a")
    public String Attribute;

    @objid ("b7a75f41-516f-433f-8384-d88eaa4e9bd0")
    public String Attribute1;

    @objid ("8595f29a-7af3-46ea-845a-e04120917f8b")
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        MainLayot mainLayot = new MainLayot();
        setContent(mainLayot);
    }

}
