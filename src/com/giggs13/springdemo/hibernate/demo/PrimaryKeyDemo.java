package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import com.giggs13.springdemo.hibernate.util.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class PrimaryKeyDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            try {
                System.out.println("Creating 3 Student objects...");
                String theDateOfBirthStr = "31/12/1998";
                Date dateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
                Student student1 = new Student("John", "Doe", "john@luv2code.com", dateOfBirth);
                Student student2 = new Student("Mary", "Public", "mary@luv2code.com", dateOfBirth);
                Student student3 = new Student("Bonita", "Applebum", "bonita@luv2code.com", dateOfBirth);
                session.beginTransaction();
                System.out.println("Saving the Student objects...");
                session.save(student1);
                session.save(student2);
                session.save(student3);
                session.getTransaction().commit();
                System.out.println("Done!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
