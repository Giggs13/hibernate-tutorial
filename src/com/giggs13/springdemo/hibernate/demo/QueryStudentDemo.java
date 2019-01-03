package com.giggs13.springdemo.hibernate.demo;

import com.giggs13.springdemo.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            List<Student> students = session.createQuery("from Student").getResultList();
            displayStudents(students);

            students = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
            displayStudents(students);

            students = session.createQuery("from Student s where s.lastName = 'Doe' or s.firstName = 'Daffy'").getResultList();
            displayStudents(students);

            students = session.createQuery("from Student where email like '%luv2code.com'").getResultList();
            displayStudents(students);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }

    private static void displayStudents(List<Student> students) {
        students.forEach(System.out::println);
        System.out.println();
    }
}
