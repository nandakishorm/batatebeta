package com.kishor.batatebeta.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 * Created by Nandakishor on 9/4/2015.
 */

@SpringUI(path = "/dash")
public class MainUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Dashboard dashboard = new Dashboard();
        setContent(dashboard);
    }
}