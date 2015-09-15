package com.kishor.batatebeta.ui;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import com.kishor.batatebeta.ui.extededComponents.BatateButton;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import javax.annotation.PostConstruct;

/**
 * Created by Nandakishor on 9/9/2015.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class UserViewLayout extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFormLayout userFormLayout;

    private Table tblUser;
    private Window winUserFormContainer;
    private Button btnUserNew, btnUserUpdate, btnUserDelete;

    @PostConstruct
    public void uiInit() {
        setSizeFull();
        userViewLayoutInit();
        configureComponentsListeners();
    }

    private void configureComponentsListeners() {

        Window.CloseListener windowCloseListener = new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                reloadTable();
            }
        };

        ItemClickEvent.ItemClickListener tableItemClickListener = new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Object selectedMemberRow = itemClickEvent.getItemId();
                if (itemClickEvent.isDoubleClick()) {
                    if (selectedMemberRow != null) {
                        Object selectedRow = itemClickEvent.getItemId();
                        winUserFormContainer = new Window("Update User");
                        winUserFormContainer.setWidth("50%");
                        winUserFormContainer.setHeight("40%");
                        winUserFormContainer.setModal(true);
                        userFormLayout.removeAllComponents();
                        userFormLayout.uiInit();
                        userFormLayout.loadRecord((User) selectedRow);
                        winUserFormContainer.setContent(userFormLayout);
                        UI.getCurrent().addWindow(winUserFormContainer);
                        winUserFormContainer.addCloseListener(windowCloseListener);
                    }
                }
            }
        };

        Button.ClickListener buttonClickListener = clickEvent -> {
            try {
                if (clickEvent.getButton().getCaption().equalsIgnoreCase("new")) {
                    winUserFormContainer = new Window("New User");
                    winUserFormContainer.setWidth("50%");
                    winUserFormContainer.setHeight("40%");
                    winUserFormContainer.setModal(true);
                    userFormLayout.removeAllComponents();
                    userFormLayout.uiInit();
                    winUserFormContainer.setContent(userFormLayout);
                    UI.getCurrent().addWindow(winUserFormContainer);
                    winUserFormContainer.addCloseListener(windowCloseListener);
                } else if (clickEvent.getButton().getCaption().equalsIgnoreCase("update")) {
                    Object selectedRow = tblUser.getValue();
                    winUserFormContainer = new Window("Update User");
                    winUserFormContainer.setWidth("50%");
                    winUserFormContainer.setHeight("40%");
                    winUserFormContainer.setModal(true);
                    userFormLayout.removeAllComponents();
                    userFormLayout.uiInit();
                    userFormLayout.loadRecord((User) selectedRow);
                    winUserFormContainer.setContent(userFormLayout);
                    UI.getCurrent().addWindow(winUserFormContainer);
                    winUserFormContainer.addCloseListener(windowCloseListener);
                } else if (clickEvent.getButton().getCaption().equalsIgnoreCase("update")) {
                    Object selectedRow = tblUser.getValue();
                    userService.delete((User) selectedRow);
                    Notification.show("Record was successfully deleted");
                }
            } catch (BatateException be) {
                Notification.show("Error", be.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        tblUser.addItemClickListener(tableItemClickListener);
        reloadTable();
        btnUserNew.addClickListener(buttonClickListener);
        btnUserUpdate.addClickListener(buttonClickListener);
        btnUserDelete.addClickListener(buttonClickListener);
    }


    private void reloadTable() {
//        BeanItemContainer<User> userBeanItemContainer = new BeanItemContainer<>(User.class);
//        userBeanItemContainer.addContainerProperty("userName", String.class, "");
//        userBeanItemContainer.addContainerProperty("fullName", String.class, "");
//        userBeanItemContainer.addContainerProperty("role", Object.class, "");
//        userBeanItemContainer.addContainerProperty("status", Object.class, "");
//        userBeanItemContainer.addAll(userService.findAll());

        BeanItemContainer<User> userBeanItemContainer = new BeanItemContainer<>(User.class);
        userBeanItemContainer.addNestedContainerProperty("userName");
        userBeanItemContainer.addNestedContainerProperty("fullName");
        userBeanItemContainer.addNestedContainerProperty("role");
        userBeanItemContainer.addNestedContainerProperty("status");
        userBeanItemContainer.addAll(userService.findAll());
        tblUser.setContainerDataSource(userBeanItemContainer);
        tblUser.setVisibleColumns("userName","fullName","role","status");
        tblUser.setColumnHeaders("Username","Full name","Role","Status");
    }

    private void userViewLayoutInit() {
        tblUser = new Table();
        tblUser.setImmediate(true);
        tblUser.setSelectable(true);
        tblUser.setSizeFull();

        btnUserNew = new BatateButton("New");
        btnUserUpdate = new BatateButton("Update");
        btnUserDelete = new BatateButton("Delete");

        HorizontalLayout hlUserCrudButtonLayout = new HorizontalLayout(btnUserNew, btnUserUpdate, btnUserDelete);
        hlUserCrudButtonLayout.setSpacing(true);
        hlUserCrudButtonLayout.setSizeUndefined();
        addComponent(hlUserCrudButtonLayout);
        addComponent(tblUser);
        setExpandRatio(hlUserCrudButtonLayout, 0.05f);
        setExpandRatio(tblUser, 0.95f);
        setMargin(true);
        setSpacing(true);
    }
}
