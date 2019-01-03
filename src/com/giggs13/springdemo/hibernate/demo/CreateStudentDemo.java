package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import com.giggs13.springdemo.hibernate.util.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class CreateStudentDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            System.out.println("Creating a new Student object...");
            String theDateOfBirthStr = "31/12/1998";
            Date dateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            Student student = new Student("Paul", "Wall", "paul@luv2code.com", dateOfBirth);
            session.beginTransaction();
            System.out.println("Saving the Student object...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
