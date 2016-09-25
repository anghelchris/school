package com.example;

import javax.servlet.annotation.WebServlet;

import com.example.dao.TeacherDao;
import com.example.domain.Teacher;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Label label = new Label("Scoala medie Nr.39");
        label.setSizeUndefined();

        HorizontalLayout header = new HorizontalLayout(label);
        header.setSizeFull();
        header.setHeight("100px");
        header.addStyleName("header");
        header.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        VerticalLayout leftSide = new VerticalLayout();
        leftSide.addStyleName("leftSide");
        leftSide.setMargin(true);
        leftSide.setSpacing(false);
        leftSide.setSizeFull();
        leftSide.setWidth("200px");

        VerticalLayout rightSide = new VerticalLayout();
        rightSide.setSizeFull();
        rightSide.setMargin(true);
        rightSide.addStyleName("rightSide");

        HorizontalLayout content = new HorizontalLayout(leftSide, rightSide);
        content.setExpandRatio(rightSide, 1f);
        content.setSizeFull();

        Button teacherBtn = new Button("Teacher");
        teacherBtn.setWidth("100%");
        teacherBtn.addClickListener(e -> {
            BeanItemContainer<Teacher> container = new BeanItemContainer<>(Teacher.class, TeacherDao.getAll());
            Table teachersTable = new Table("Teachers", container);
            teachersTable.setPageLength(0);
            teachersTable.setSizeFull();
            teachersTable.addStyleName("table");
            teachersTable.setColumnReorderingAllowed(true);

//            teachersTable.addGeneratedColumn("ageString", new Table.ColumnGenerator() {
//                @Override
//                public Object generateCell(Table source, Object itemId, Object columnId) {
//                    return String.valueOf(((Teacher) itemId).getAge());
//                }
//            });

            teachersTable.setVisibleColumns("firstName", "lastName", "age");
            teachersTable.setColumnHeaders("First Name", "Last Name", "Age");

            TextField filter = new TextField();
            filter.setIcon(FontAwesome.FILTER);
            filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
                Or or = null;

                @Override
                public void textChange(FieldEvents.TextChangeEvent event) {
                    Container.Filterable filterable = (Container.Filterable) teachersTable.getContainerDataSource();

                    if (or != null) filterable.removeContainerFilter(or);

                    if (event.getText() != null) {
                        filterable.addContainerFilter(or = new Or(new SimpleStringFilter("firstName", event.getText(), true, false)
                                , new SimpleStringFilter("lastName", event.getText(), true, false)
                            , new SimpleStringFilter("ageString", event.getText(), true, false)));
                    } else

                    filterable.addContainerFilter(or = new Or(new SimpleStringFilter("firstName", event.getText(), true, false)
                            , new SimpleStringFilter("lastName", event.getText(), true, false)
//                            , new SimpleStringFilter("ageString", event.getText(), true, false)
                            , new Compare.Equal("age", Integer.parseInt(event.getText()))
                    ));
                }
            });
            FormLayout filterWrapper = new FormLayout(filter);
            filterWrapper.setComponentAlignment(filter, Alignment.MIDDLE_RIGHT);
            filterWrapper.setMargin(false);
            filterWrapper.setSizeUndefined();

            rightSide.removeAllComponents();
            rightSide.addComponents(filterWrapper, teachersTable);
            rightSide.setComponentAlignment(filterWrapper, Alignment.MIDDLE_RIGHT);
            rightSide.setExpandRatio(filterWrapper, 0.12f);
            rightSide.setExpandRatio(teachersTable, 0.88f);

        });

//        Button studentBtn = new Button("Student");
//        studentBtn.setWidth("100%");
//        studentBtn.addClickListener(e -> {
//            BeanItemContainer<Student> container = new BeanItemContainer<>(Student.class, dataBase.getAllStudents());
//
//            Table students = new Table("Students", container);
//            students.setPageLength(0);
//            students.setSizeFull();
//            students.addStyleName("table");
//            students.setColumnReorderingAllowed(true);
//            students.setVisibleColumns("firstName", "lastName", "age", "year", "group");
//            students.setColumnHeaders("First Name", "Last Name", "Age", "Year", "Group");
//
//            rightSide.removeAllComponents();
//            rightSide.addComponent(students);
//        });

        VerticalLayout layout = new VerticalLayout(teacherBtn);//, studentBtn);
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
