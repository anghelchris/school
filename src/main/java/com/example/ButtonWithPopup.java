package com.example;

import com.example.domain.Teacher;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ButtonWithPopup extends HorizontalLayout {

    private Button button;
    private String caption;
    private PopupView popup;
    private Button save;
    private Button cancel;
    private Teacher teacher;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField ageField;

    public ButtonWithPopup(String caption, Teacher teacher) {
        this.caption = caption;
        this.teacher = teacher;

        popup = buildPopup();

        button = new Button();
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                fillFields();
                popup.setPopupVisible(true);
            }
        });

        addComponents(popup, button);
    }

    public void fillFields() {
        firstNameField.setValue(teacher.getFirstName());
        lastNameField.setValue(teacher.getLastName());
        ageField.setValue(String.valueOf(teacher.getAge()));
    }

    private PopupView buildPopup() {
        PopupView popup = new PopupView(null, createPanel());
        popup.addStyleName("styledPopup");
        popup.setHideOnMouseOut(false);

        return popup;
    }

    private Panel createPanel() {
        final Panel panel = new Panel(caption);
        panel.addStyleName("styledPopupCaption");

        // Add some components
        // Buttons Layout:
        save = new Button("Save");
        save.setIcon(FontAwesome.SAVE);

        cancel = new Button("Cancel");

        // Create a layout inside the panel:
        final VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);

        firstNameField = new TextField("First Name:");
        firstNameField.setNullRepresentation("");
        lastNameField = new TextField("Last Name:");
        lastNameField.setNullRepresentation("");
        ageField = new TextField("Age:");
        ageField.setNullRepresentation("");


        HorizontalLayout controlsLayout = new HorizontalLayout(save, cancel);
        controlsLayout.setSpacing(true);
        contentLayout.addComponents(firstNameField, lastNameField, ageField, controlsLayout);

        // Set the layout as the root layout of the panel
        panel.setContent(contentLayout);
        return panel;
    }

    public void closePopup() {
        popup.setPopupVisible(false);
    }

    public Button getButton() {
        return button;
    }

    public Button getSave() {
        return save;
    }

    public Button getCancel() {
        return cancel;
    }

    public PopupView getPopup() {
        return popup;
    }

    public TextField getAgeField() {
        return ageField;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setIcon(FontAwesome icon) {
        button.setIcon(icon);
    }
    public void addStyleNames(String... styleNames) {
        for (String styleName : styleNames)
            button.addStyleName(styleName);
    }

//    @Override
//    public void setIcon(Resource icon) {
//        super.setIcon(icon);
//    }
}
