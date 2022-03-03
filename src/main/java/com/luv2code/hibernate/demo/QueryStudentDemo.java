package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{

            // start a transaction
            session.beginTransaction();

            // query students
            List theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query students: lastName = 'Gin'
            theStudents = session.createQuery("from Student s where s.lastName='Gin'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name as Gin");
            displayStudents(theStudents);

            // query students: lastName='Doe' OR firstName='Santosh'
            theStudents = session.createQuery("from Student s where"
                                        +" s.lastName='Gin' OR s.firstName='Santosh'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name as Gin OR first name as Santosh");
            displayStudents(theStudents);

            // query students where email LIKE '%@zemosolabs.com'
            theStudents = session.createQuery("from Student s where"
                    + " s.email LIKE '%zemosolabs.com'").getResultList();

            // display the students
            System.out.println("\n\nStudents whose email ends with zemosolabs.com");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally{
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

}
