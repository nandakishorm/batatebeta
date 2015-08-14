package com.kishor.batatebeta.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * Created by Nandakishor on 8/10/2015.
 */
public class Dashboard extends VerticalLayout {

    public Dashboard() {
        setSizeFull();
        configureLayouts();
    }

    private void configureLayouts() {

        Button btnUser = new NativeButton("Users");
        btnUser.setWidth("100%");
        btnUser.setHeight(70, Unit.PIXELS);
//        btnUser.setWidth(150, Unit.PIXELS);

        Button btnAccount = new NativeButton("Accounts");
        btnAccount.setWidth("100%");
        btnAccount.setHeight(70, Unit.PIXELS);
//        btnAccount.setWidth(150, Unit.PIXELS);

        VerticalLayout vlButtonContainerSubSet = new VerticalLayout(btnUser, btnAccount);
        vlButtonContainerSubSet.setWidth("100%");

        VerticalLayout vlButtonContainer = new VerticalLayout(vlButtonContainerSubSet);
        vlButtonContainer.setSizeFull();

        VerticalLayout vlDetailViewContainer = new VerticalLayout();
        vlDetailViewContainer.setSizeFull();

        HorizontalLayout hlHeader = new HorizontalLayout(
                new Label("<h1>Header Contents</h1>", ContentMode.HTML)
        );

        HorizontalLayout hlComponentContainer = new HorizontalLayout();
        hlComponentContainer.setSizeFull();
        hlComponentContainer.addComponent(vlButtonContainer);
        hlComponentContainer.addComponent(vlDetailViewContainer);
        hlComponentContainer.setExpandRatio(vlButtonContainer, 0.08f);
        hlComponentContainer.setExpandRatio(vlDetailViewContainer, 0.92f);

        Panel pnlDetailView = new Panel();
        pnlDetailView.setSizeFull();
        vlDetailViewContainer.addComponent(pnlDetailView);

        addComponent(hlHeader);
        addComponent(hlComponentContainer);
        setExpandRatio(hlHeader, 0.07f);
        setExpandRatio(hlComponentContainer, 0.93f);
    }
}
