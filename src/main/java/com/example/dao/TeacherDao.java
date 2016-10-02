package com.example.dao;

import com.example.HibernateUtil;
import com.example.domain.Teacher;
import org.hibernate.Session;

import java.util.List;

public class TeacherDao {

    public static List<Teacher> getAll() {

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.getTransaction().begin();

        List<Teacher> teachers = s.createCriteria(Teacher.class).list();

        s.getTransaction().commit();

        s.close();

        return teachers;
    }

    public static void  update(Teacher teacher) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.getTransaction().begin();

        s.update(teacher);

        s.getTransaction().commit();

        s.close();
    }
}
