package com.github.turchev.carrsh;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@Route("")
@PageTitle("My")
public class HelloWorld extends Div {
   public HelloWorld() {
       this.add(new H1("Hello World!"));
   }
}

