package com.example;

import com.example.dao.StudentDao;
import com.example.dao.TeacherDao;
import com.example.domain.Student;
import com.example.domain.Teacher;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.MSize;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.label.MLabel;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
public class MyUI extends UI {

    private final String TEACHER = "firstName";

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        MLabel label = new MLabel("Scoala medie Nr.39")
                .withWidthUndefined();

        MHorizontalLayout header = new MHorizontalLayout(label)
                .withFullWidth()
                .withHeight("100px")
                .withStyleName("header")
                .withAlign(label, Alignment.MIDDLE_CENTER);

        MVerticalLayout leftSide = new MVerticalLayout()
                .withStyleName("leftSide")
//                .withMargin(true)
                .withSpacing(true)
                .withSize(MSize.FULL_SIZE)
                .withWidth("200px");


        MVerticalLayout rightSide = new MVerticalLayout()
                .withSize(MSize.FULL_SIZE);
//                .withMargin(true)
//                .withStyleName("rightSide");

        MHorizontalLayout content = new MHorizontalLayout(leftSide, rightSide)
                .withExpand(rightSide, 1f)
                .withSize(MSize.FULL_SIZE);

        MButton teacherBtn = new MButton("Teacher", e -> {

            BeanItemContainer<Teacher> container = new BeanItemContainer<>(Teacher.class, TeacherDao.getAll());

            MTable<Teacher> teachersTable = new MTable<Teacher>()
                .withCaption("Teachers")
                .withSize(MSize.FULL_SIZE)
                .withStyleName("table");

            teachersTable.setContainerDataSource(container);
            teachersTable.setPageLength(0);
            teachersTable.setColumnReorderingAllowed(true);
            teachersTable.addGeneratedColumn("Edit", (Table table, Object itemId, Object columnId) -> {

                ButtonWithPopup editBtn = new ButtonWithPopup("Edit Teacher", (Teacher) itemId);
                editBtn.setIcon(FontAwesome.EDIT);
                editBtn.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.BUTTON_ICON_ONLY);
                editBtn.getSave().addClickListener(event -> {

                    BeanItem<Teacher> beanItem = container.getItem(itemId);
                    beanItem.getItemProperty("firstName").setValue(editBtn.getFirstNameField().getValue());
                    beanItem.getItemProperty("lastName").setValue(editBtn.getLastNameField().getValue());

                    try {
                        int age = Integer.valueOf(editBtn.getAgeField().getValue());
                        beanItem.getItemProperty("age").setValue(age);
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }

                    TeacherDao.update(beanItem.getBean());
                    editBtn.closePopup();
                });
                editBtn.getCancel().addClickListener(click -> editBtn.closePopup());
                return editBtn;
            });

            teachersTable.setVisibleColumns("firstName", "lastName", "age", "Edit");
            teachersTable.setColumnHeaders("First Name", "Last Name", "Age", "");

            teachersTable.setColumnAlignment("Edit", Table.Align.CENTER);
            teachersTable.setColumnExpandRatio("firstName", 0.35f);
            teachersTable.setColumnExpandRatio("lastName", 0.35f);
            teachersTable.setColumnExpandRatio("age", 0.3f);
//            teachersTable.setColumnExpandRatio("Edit", 0.1f);
            teachersTable.setColumnWidth("Edit", 45);


            TextField filter = new TextField();
            filter.setIcon(FontAwesome.FILTER);
            filter.addTextChangeListener(event -> {

                container.removeAllContainerFilters();

                try {
                    int parseInt = Integer.parseInt(event.getText());
                    container.addContainerFilter(new Compare.Equal("age", parseInt));

                } catch (NumberFormatException nfc) {
                    container.addContainerFilter(new Or(
                                    new SimpleStringFilter("firstName", event.getText(), true, false),
                                    new SimpleStringFilter("lastName", event.getText(), true, false)
                            )
                    );
                }
            });

            MFormLayout filterWrapper = new MFormLayout(filter)
                    .withMargin(false)
                    .withWidthUndefined()
                    .withHeightUndefined();
//            filterWrapper.setComponentAlignment(filter, Alignment.MIDDLE_RIGHT);

            // TODO: in stil viritin
            rightSide.removeAllComponents();
            rightSide.addComponents(filterWrapper, teachersTable);
            rightSide.setComponentAlignment(filterWrapper, Alignment.MIDDLE_RIGHT);
            rightSide.setExpandRatio(filterWrapper, 0.12f);
            rightSide.setExpandRatio(teachersTable, 0.88f);
        }
        ).withWidth("100%");


        Button studentBtn = new Button("Student");
        studentBtn.setWidth("100%");
        studentBtn.addClickListener(e -> {

            BeanItemContainer<Student> container = new BeanItemContainer<>(Student.class, StudentDao.getAll());

            Table studentsTable = new Table("Students", container);
            studentsTable.setPageLength(0);
            studentsTable.setSizeFull();
            studentsTable.addStyleName("table");
            studentsTable.setColumnReorderingAllowed(true);
            studentsTable.setVisibleColumns("firstName", "lastName", "age");
            studentsTable.setColumnHeaders("First Name", "Last Name", "Age");
            studentsTable.addGeneratedColumn("Edit", new Table.ColumnGenerator() {
                @Override
                public Object generateCell(Table source, Object itemId, Object columnId) {
                    return null;
                }
            });

            TextField filter = new TextField();
            filter.setIcon(FontAwesome.FILTER);
            filter.addTextChangeListener(event -> {

                container.removeAllContainerFilters();

                try {
                    int parseInt = Integer.parseInt(event.getText());
                    container.addContainerFilter(new Compare.Equal("age", parseInt));

                } catch (NumberFormatException nfc) {
                    container.addContainerFilter(new Or(
                                    new SimpleStringFilter("firstName", event.getText(), true, false),
                                    new SimpleStringFilter("lastName", event.getText(), true, false)
                            )
                    );
                }
            });

            FormLayout filterWrapper = new FormLayout(filter);
            filterWrapper.setComponentAlignment(filter, Alignment.MIDDLE_RIGHT);
            filterWrapper.setMargin(false);
            filterWrapper.setSizeUndefined();

            rightSide.removeAllComponents();
            rightSide.addComponents(filterWrapper, studentsTable);
            rightSide.setComponentAlignment(filterWrapper, Alignment.MIDDLE_RIGHT);
            rightSide.setExpandRatio(filterWrapper, 0.12f);
            rightSide.setExpandRatio(studentsTable, 0.88f);
        });


        VerticalLayout layout = new VerticalLayout(teacherBtn, studentBtn);
        layout.setSpacing(true);
        layout.setSizeFull();

        Panel panel = new Panel(layout);
        panel.setStyleName("leftSide");
        leftSide.addComponent(panel);

        VerticalLayout mainLayout = new VerticalLayout(header, content);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(content, 0.1f);
        setContent(mainLayout);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
