package com.kishor.batatebeta.ui;

import com.kishor.batatebeta.core.dictionary.Role;
import com.kishor.batatebeta.core.dictionary.Status;
import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.BatateMailSenderService;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import com.kishor.batatebeta.ui.extededComponents.BatateButton;
import com.kishor.batatebeta.ui.extededComponents.BatateComboBox;
import com.kishor.batatebeta.ui.extededComponents.BatateTextField;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.File;

/**
 * Created by Nandakishor on 9/4/2015.
 */

@SpringComponent
@Scope("prototype")
public class UserFormLayout extends VerticalLayout {

    @Autowired
    private BatateMailSenderService batateMailSenderService;

    @Autowired
    private UserService userService;

    private Button btnSave, btnReset;
    private TextField txtUid, txtFullName, txtUserName, txtEmail;
    private PasswordField pasPassword;
    private ComboBox cmbRole, cmbStatus;
    private CheckBox chkSendEmailToAdmin;

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
            boolean mailSenderStatus;
            try {
                if (clickEvent.getButton().getCaption().equalsIgnoreCase("save")) {
                    User user = new User();
                    user.setUid(txtUid.getValue());
                    user.setFullName(txtFullName.getValue());
                    if (!txtEmail.isValid()) {
                        Notification.show("Invalid input","Please enter valid inputs", Notification.Type.TRAY_NOTIFICATION);
                        return;
                    }
                    user.setEmail(txtEmail.getValue());
                    user.setUserName(txtUserName.getValue());
                    user.setPassword(pasPassword.getValue());
                    user.setStatus(Status.valueOf(cmbStatus.getValue().toString()));
                    user.setRole(Role.valueOf(cmbRole.getValue().toString()));
                    if (user.getUid().isEmpty()) {
                        user = userService.create(user);
                        if (chkSendEmailToAdmin.getValue()) {
                            mailSenderStatus = batateMailSenderService.sendMailCreateUser();
                            if(!mailSenderStatus)
                                Notification.show("Unable to send user creation email", Notification.Type.WARNING_MESSAGE);
                        }
                    } else
                        user = userService.update(user);
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
        txtUid = new BatateTextField("UID");
        txtUid.setVisible(false);
        txtFullName = new BatateTextField("FullName");
        txtUserName = new BatateTextField("UserName");
        txtEmail = new BatateTextField("Email");
        txtEmail.addValidator(new EmailValidator("Invalid email id !!!"));
        txtEmail.setImmediate(true);
        pasPassword = new PasswordField("Password");
        pasPassword.setWidth("100%");
        cmbRole = new BatateComboBox("Role");
        cmbRole.addItems(Role.values());
        cmbRole.setNullSelectionAllowed(false);
        cmbRole.select(Role.USER);
        cmbStatus = new BatateComboBox("Status");
        cmbStatus.addItems(Status.getValues());
        cmbStatus.setNullSelectionAllowed(false);
        cmbStatus.select(Status.ACTIVE);
        chkSendEmailToAdmin = new CheckBox("Send email notification to Admin");

        btnSave = new BatateButton("Save");
        btnReset = new BatateButton("Reset");

        FormLayout flUserLayout = new FormLayout(
                txtUid, txtFullName, txtEmail, txtUserName, pasPassword, cmbRole, cmbStatus, chkSendEmailToAdmin
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
        txtUid.setValue(user.getUid());
        txtFullName.setValue(user.getFullName());
        txtEmail.setValue(user.getEmail());
        txtUserName.setValue(user.getUserName());
        pasPassword.setInputPrompt("xxxx");
        cmbRole.setValue(user.getRole());
        cmbStatus.setValue(user.getStatus());
        chkSendEmailToAdmin.setVisible(false);
    }

    public void resetForm() {
        txtUid.setValue("");
        txtFullName.setValue("");
        txtUserName.setValue("");
        txtEmail.setValue("");
        pasPassword.setInputPrompt("xxxx");
        cmbRole.setValue(null);
        cmbStatus.setValue(null);
        chkSendEmailToAdmin.setValue(false);
    }
}
