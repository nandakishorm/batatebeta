package com.kishor.batatebeta.ui;

import com.kishor.batatebeta.core.dictionary.Role;
import com.kishor.batatebeta.core.dictionary.Status;
import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import com.kishor.batatebeta.ui.extededComponents.BatateButton;
import com.kishor.batatebeta.ui.extededComponents.BatateComboBox;
import com.kishor.batatebeta.ui.extededComponents.BatateTextField;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * Created by Nandakishor on 9/4/2015.
 */

@SpringComponent
@Scope("prototype")
public class UserFormLayout extends VerticalLayout {

    @Autowired
    private UserService userService;

    private Button btnSave, btnReset;
    private TextField txtFullName, txtUserName;
    private PasswordField pasPassword;
    private ComboBox cmbRole, cmbStatus;

    public UserFormLayout() {
    }

    @PostConstruct
    public void uiInit() {
        userFormLayoutInit();
        configureComponentListeners();
        setSizeFull();
    }

    private void configureComponentListeners() {
        Button.ClickListener clickListener = clickEvent -> {
            try {
                if (clickEvent.getButton().getCaption().equalsIgnoreCase("save")) {
                    User user = new User();
                    user.setFullName(txtFullName.getValue());
                    user.setUserName(txtUserName.getValue());
                    user.setPassword(pasPassword.getValue());
                    user.setStatus(Status.valueOf(cmbStatus.getValue().toString()));
                    user.setRole(Role.valueOf(cmbRole.getValue().toString()));
                    user = userService.create(user);
                    Notification.show("New user was created successfully");
                } else if (clickEvent.getButton().getCaption().equalsIgnoreCase("reset")) {
                    resetForm();
                }
            } catch (BatateException be) {
                be.printStackTrace();
            }
        };
        btnSave.addClickListener(clickListener);
        btnReset.addClickListener(clickListener);
    }

    private void userFormLayoutInit() {
        txtFullName = new BatateTextField("FullName");
        txtUserName = new BatateTextField("UserName");
        pasPassword = new PasswordField("Password");
        pasPassword.setWidth("100%");
        cmbRole = new BatateComboBox("Role");
        cmbRole.addItems(Role.values());
        cmbRole.select(Role.USER);
        cmbStatus = new BatateComboBox("Status");
        cmbStatus.addItems(Status.values());
        cmbStatus.select(Status.Active);
        btnSave = new BatateButton("Save");
        btnReset = new BatateButton("Reset");

        FormLayout flUserLayout = new FormLayout(
                txtFullName, txtUserName, pasPassword, cmbRole, cmbStatus
        );
        flUserLayout.setSizeFull();
        HorizontalLayout hlButtons = new HorizontalLayout(btnSave, btnReset);
        hlButtons.setSpacing(true);

        HorizontalLayout hlButtonsOuter = new HorizontalLayout(hlButtons);
        hlButtonsOuter.setWidth("100%");
        hlButtonsOuter.setComponentAlignment(hlButtons, Alignment.MIDDLE_RIGHT);

        FormLayout flFormContainer = new FormLayout(flUserLayout, hlButtonsOuter);
        flFormContainer.setWidth("75%");

        addComponent(flFormContainer);
        setComponentAlignment(flFormContainer, Alignment.TOP_CENTER);
    }

    public void loadRecord(User user) {
        txtFullName.setValue(user.getFullName());
        txtUserName.setValue(user.getUserName());
        pasPassword.setInputPrompt("xxxx");
        cmbRole.setValue(user.getRole());
        cmbStatus.setValue(user.getStatus());
    }

    public void resetForm() {
        txtFullName.setValue("");
        txtUserName.setValue("");
        pasPassword.setInputPrompt("xxxx");
        cmbRole.setValue(null);
        cmbStatus.setValue(null);
    }
}
