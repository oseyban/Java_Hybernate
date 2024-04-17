package com.hb03.uni_onetoone;

import com.hb02.embeddable.Student02;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FetchDiary {
    public static void main(String[] args) {
        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student03.class).
                addAnnotatedClass(Diary.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        try {
            session.beginTransaction();

            Diary diary1 = session.get(Diary.class, 101);
            Student03 student1 = diary1.getStudent();

            System.out.println("Öğrencinin Günlüğü:");
            System.out.println(diary1);

            Query<Diary> query = session.createQuery("FROM Diary", Diary.class);
            List<Diary> diaryList = query.getResultList();

            System.out.println("----------------- Öğrencilerin Günlükleri-----------------");
            for (Diary diary : diaryList) {
                System.out.println(diary);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
}
