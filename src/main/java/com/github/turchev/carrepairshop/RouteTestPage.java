package com.github.turchev.carrepairshop;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
@PageTitle("TestPage")
public class RouteTestPage extends Div {
   public RouteTestPage() {
       this.add(new H1("Hello World!"));
   }
}
