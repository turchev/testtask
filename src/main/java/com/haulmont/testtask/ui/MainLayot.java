package com.haulmont.testtask.ui;

import com.haulmont.testtask.data.entity.EntityType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@objid ("7d992f7b-17ad-4ea4-a052-d4aa805e2ef6")
@SuppressWarnings("serial")
public class MainLayot extends VerticalLayout {
    @objid ("9bc9762a-077e-49e0-97e6-8f0c0201c04c")
    public MainLayot() {
        final FormHelper helper = new FormHelper();
        this.setMargin(true);
        
        final TabSheet tabsheet = new TabSheet(); 
        tabsheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
                
        TabSheet.Tab tab1 = tabsheet.addTab(helper.getPage(EntityType.CLIENT));        
        tab1.setCaption("Клиенты");
        tab1.setDescription("Список клиентов");
                
        TabSheet.Tab tab2 = tabsheet.addTab(helper.getPage(EntityType.MECHANIC));
        tab2.setCaption("Механики");
        tab2.setDescription("Список механиков");
        
        TabSheet.Tab tab3 = tabsheet.addTab(helper.getPage(EntityType.ORDER));
        tab3.setCaption("Заказы");
        tab3.setDescription("Список заказов");
                        
        this.addComponent(tabsheet);
    }

}
