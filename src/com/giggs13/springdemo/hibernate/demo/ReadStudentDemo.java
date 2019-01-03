package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import com.giggs13.springdemo.hibernate.util.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ReadStudentDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            try {
                System.out.println("Creating a new Student object...");
                String theDateOfBirthStr = "31/12/1998";
                Date dateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
                Student student = new Student("Daffy", "Duck", "daffy@luv2code.com", dateOfBirth);
                session.beginTransaction();
                System.out.println("Saving the Student object...\n" + student);
                session.save(student);
                session.getTransaction().commit();
                System.out.println("Saved student. Generated id: " + student.getId());

                session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                System.out.println("\nGetting Student with id: " + student.getId());
                Student myStudent = session.get(Student.class, student.getId());
                System.out.println("Get complete: " + myStudent);
                session.getTransaction().commit();
                System.out.println("Done!");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
