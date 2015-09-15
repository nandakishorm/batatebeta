package com.kishor.batatebeta.ui;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by Nandakishor on 8/10/2015.
 */

@SpringComponent
@Scope("prototype")
public class Dashboard extends VerticalLayout {

    @Autowired
    private UserViewLayout userViewLayout;

    Button btnUser, btnAccount;
    Panel pnlDetailView;
    VerticalLayout vlDetailViewContainer;
    HorizontalLayout hlHeader, hlComponentContainer;

    @PostConstruct
    public void uiInit() {
        setSizeFull();
        setCaption("New User");
        layoutsInit();
        configureComponentListeners();
    }

    private void configureComponentListeners() {

        Button.ClickListener clickListener = clickEvent -> {
            if (clickEvent.getButton().getCaption().equalsIgnoreCase("users")) {
                pnlDetailView.setContent(userViewLayout);
            } else if (clickEvent.getButton().getCaption().equalsIgnoreCase("accounts")) {

            }
        };
        btnUser.addClickListener(clickListener);
        btnAccount.addClickListener(clickListener);
    }

    private void layoutsInit() {

        btnUser = new NativeButton("Users");
        btnUser.setWidth("100%");
        btnUser.setHeight(70, Unit.PIXELS);
//        btnUser.setWidth(150, Unit.PIXELS);

        btnAccount = new NativeButton("Accounts");
        btnAccount.setWidth("100%");
        btnAccount.setHeight(70, Unit.PIXELS);
//        btnAccount.setWidth(150, Unit.PIXELS);

        VerticalLayout vlButtonContainerSubSet = new VerticalLayout(btnUser, btnAccount);
        vlButtonContainerSubSet.setWidth("100%");

        VerticalLayout vlButtonContainer = new VerticalLayout(vlButtonContainerSubSet);
        vlButtonContainer.setSizeFull();

        vlDetailViewContainer = new VerticalLayout();
        vlDetailViewContainer.setSizeFull();

        Image batateLogo = new Image("", new ThemeResource("potato.jpg"));
        batateLogo.setHeight(70f, Unit.PIXELS);
        batateLogo.setWidth(100f, Unit.PIXELS);
        batateLogo.setAlternateText("Batate");
        hlHeader = new HorizontalLayout(
                batateLogo,
                new Label("<h1>Header Contents</h1>", ContentMode.HTML)
        );
        hlHeader.setSpacing(true);

        hlComponentContainer = new HorizontalLayout();
        hlComponentContainer.setSizeFull();
        hlComponentContainer.addComponent(vlButtonContainer);
        hlComponentContainer.addComponent(vlDetailViewContainer);
        hlComponentContainer.setExpandRatio(vlButtonContainer, 0.08f);
        hlComponentContainer.setExpandRatio(vlDetailViewContainer, 0.92f);

        pnlDetailView = new Panel();
        pnlDetailView.setSizeFull();
        vlDetailViewContainer.addComponent(pnlDetailView);

        addComponent(hlHeader);
        addComponent(hlComponentContainer);
        setExpandRatio(hlHeader, 0.07f);
        setExpandRatio(hlComponentContainer, 0.93f);
    }
}
