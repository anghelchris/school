package com.example.dao;

import com.example.domain.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by angel on 9/21/16.
 */
public class TeacherDao {

   public static List<Teacher> getAll() {

       Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

       StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
               .applySettings(cfg.getProperties());
       ServiceRegistry service = builder.build();

       SessionFactory sf = cfg.buildSessionFactory(service);
       Session s = sf.openSession();
       s.getTransaction().begin();

       List<Teacher> teachers = s.createCriteria(Teacher.class).list();

       s.getTransaction().commit();

       s.close();
       sf.close();

       return teachers;
   }
}
