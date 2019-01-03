package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            int studentId = 7;
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("Get complete: " + myStudent);
            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");
            session.getTransaction().commit();

            System.out.println("Updating all the students...");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
