package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            int studentId = 2;
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("Get complete: " + myStudent);
            System.out.println("Deleting student...");
            if (Objects.nonNull(myStudent)) {
                session.delete(myStudent);
            }
            session.getTransaction().commit();

            System.out.println("Deleting another student...");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Student where id = 5").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
