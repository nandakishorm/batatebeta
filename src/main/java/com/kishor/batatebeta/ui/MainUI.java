package com.kishor.batatebeta.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nandakishor on 9/4/2015.
 */

//@Component
//@Scope("prototype")
@SpringUI(path = "/dash")
public class MainUI extends UI {

    @Autowired
    private Dashboard dashboard;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
//        Dashboard dashboard = new Dashboard();
        setContent(dashboard);
    }
}