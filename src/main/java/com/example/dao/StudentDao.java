package com.example.dao;

import com.example.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class StudentDao {
    public static List<Student> getAll() {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        ServiceRegistry service = builder.build();

        SessionFactory sf = cfg.buildSessionFactory(service);
        Session s = sf.openSession();
        s.getTransaction().begin();

        List<Student> students = s.createCriteria(Student.class).list();

        s.getTransaction().commit();

        s.close();
        sf.close();

        return students;
    }
}
