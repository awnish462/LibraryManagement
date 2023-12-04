package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int x=0;
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session s = factory.openSession();

        while (x!=1) {
            System.out.println("Library Management");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("1 : New Entry");
            System.out.println("2 : See Data");
            System.out.println("3 : Update Data");
            System.out.println("4 : Delete Data");
            System.out.println("5 : Exit");


            System.out.println("Choose");
            int choose = Integer.parseInt(bufferedReader.readLine());
            Student student = new Student();


            switch (choose) {
                case 1:
                    newEntry(s, bufferedReader, student);
                    break;
                case 2:
                    seeData(s, bufferedReader, student);
                    break;
                case 3:
                    updateData(s, bufferedReader);
                    break;
                case 4:
                    deleteData(s, bufferedReader, student);
                    break;
                case 5:
                    exit();

                default:
                    System.out.println("Wrong input");
            }
        }


            s.close();
            factory.close();
    }


    private static void exit() {
        System.out.println("Thank you for visiting");
        x=1;
    }

    private static void deleteData(Session s, BufferedReader bufferedReader, Student student) throws IOException {
        System.out.println("Enter the Id of student whom you want to delete");

        Transaction tx=s.beginTransaction();
        int id= Integer.parseInt(bufferedReader.readLine());

        Query q=s.createQuery("delete from Student where student_id=:s");
        q.setParameter("s",id);

        q.executeUpdate();
        System.out.println("Deleted.....");
        tx.commit();

    }

    private static void updateData(Session s, BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter Id Of Student whom you want to change data");
        Transaction tx=s.beginTransaction();
        int id= Integer.parseInt(bufferedReader.readLine());

        Query q=s.createQuery("update Student set student_name =:s, mobile_number=:m where student_id=:i");
        q.setParameter("i",id);
        System.out.println("Enter Name");
        String name=bufferedReader.readLine();
        q.setParameter("s",name);
        System.out.println("Enter Mobile_Number");
        String  mob_number=bufferedReader.readLine();
        q.setParameter("m",mob_number);
        q.executeUpdate();

        tx.commit();

    }

    private static void seeData(Session s, BufferedReader bufferedReader, Student student) throws IOException {
        System.out.println("Enter Student Id");
        int id= Integer.parseInt(bufferedReader.readLine());

        student=s.get(Student.class,id);
        if (student!=null){
            System.out.println("Student Name : "+ " " + student.getStudent_name() + " , "+" Mobile Number : " + student.getMobile_number());

        }else {
            System.out.println("Data not found");
        }

    }

    private static void newEntry(Session s, BufferedReader bufferedReader, Student student) throws IOException {
        System.out.println("Enter Name");
        String name=bufferedReader.readLine();
        student.setStudent_name(name);
        System.out.println("Enter Mobile Number");
        String  mob_number=bufferedReader.readLine();
        student.setMobile_number(mob_number);
        student.setDate(new java.util.Date());
        Transaction tx=s.beginTransaction();
        s.save(student);
        tx.commit();
    }
}