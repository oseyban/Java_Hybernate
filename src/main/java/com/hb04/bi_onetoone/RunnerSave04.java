package com.hb04.bi_onetoone;

import com.hb03.uni_onetoone.Diary;
import com.hb03.uni_onetoone.Student03;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RunnerSave04 {
    public static void main(String[] args) {
        // 3 adet student nesnesi oluşturalım
        Student04 student1=new Student04();
        student1.setId(1001);
        student1.setName("Ayşe Hanım");
        student1.setGrade(10);

        Student04 student2=new Student04();
        student2.setId(1002);
        student2.setName("Fatma Hanım");
        student2.setGrade(11);

        Student04 student3=new Student04();
        student3.setId(1003);
        student3.setName("Kenan Bey");
        student3.setGrade(9);

        //2 adet diary nesnesi oluşturalım
        Diary04 diary1=new Diary04();
        diary1.setId(101);
        diary1.setName("Ayşe Hanımın Günlüğü");
        diary1.setStudent(student1);
        // Not: ilişki kurulabilmesi için diary2.setStudent yapıldı
        // bunun yerine sadece student2.setDiary yapılsaydı ilişki kurulmayacak, DB de Diary kisminda
        // std_id kolonu null olacakti başka bir tabirle Diary tablosunda yeni bir kolon oluşacakti ama
        // degerleri null olacakti


        Diary04 diary2=new Diary04();
        diary2.setId(102);
        diary2.setName("Fatma Hanımın Günlüğü");
        diary2.setStudent(student2);

        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.save(student1);
        session.save(student2);
        session.save(student3);

        session.save(diary1);
        session.save(diary2);


        tx.commit();
        session.close();
        sf.close();

    }
}
