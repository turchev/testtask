package com.haulmont.testtask.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.VerticalLayout;

@objid ("bd055d5e-91c4-4c54-94ef-c07a56d1df3d")
@SuppressWarnings("serial")
public class MainLayot extends VerticalLayout {
    @objid ("cecdeb32-c772-4fc3-ac8c-ddfd20d6ac32")
    private MainTabSheet tabSheet;
    
    public MainLayot() {
        tabSheet = new MainTabSheet();  
        this.addComponent(tabSheet);
    }

}
