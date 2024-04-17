package com.hb04.bi_onetoone;

import com.hb03.uni_onetoone.Student03;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RunnerFetch04 {
    public static void main(String[] args) {


        Configuration con = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(Student04.class).addAnnotatedClass(Diary04.class);

        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Student04 student = session.get(Student04.class, 1001);
        Diary04 diary04=session.get(Diary04.class,101);

        System.out.println(student);
        System.out.println("==============================");
        System.out.println(student.getDiary());
        System.out.println("==============================");
        System.out.println(diary04);
        System.out.println("==============================");
        System.out.println(diary04.getStudent());


        //Task : Diary si olan öğrencilerin isimlerini ve günlüklerinin isimlerini getiriniz...
        //HQL ile :
        String hqQuery1="SELECT s.name, d.name FROM Student04 s INNER JOIN FETCH Diary04 d ON s.id=d.student";   // burada son kısım d.studen.id yazmak gerekirken hibernate sol taraftaki id kısmından dolayı sağ tarafta .id yazmasak dahi student tablosundaki id yi bulur...
        //SQL ile yazsaydık :
        // SELECT s.std_name, d.name FROM student04 Inner JOIN Diary04 d ON s.id=d.std_id

        //session.createQuery(hqQuery1, Student04.class); // 2 parametreli daha uygun ama her zaman 2 parametreli gelmeyebilir, tek parametre ile çalışmak gerekebilir . :
        List<Object[]> resultList1 = session.createQuery(hqQuery1).getResultList();

        System.out.println("==========================================");
        for (Object[] objects:resultList1){
            System.out.println(Arrays.toString(objects));
        }

        //aynı işi lambda ile yapalım
        System.out.println("==========================================");
        resultList1.forEach(t->{System.out.println(Arrays.toString(t));
        // resultList1.forEach(oa->{System.out.println(Arrays.toString(oa));   //oa değişken ismi...! bu şekilde de yazsak bir şey değişmez...
        });


        //TASK bütün öğrencileri ve varsa bu öğrencilerin kitaplarını da getirelim...
        String hqlQuery="SELECT s.name, d.name FROM Student04 s LEFT JOIN Diary04 d ON s.id=d.student";

        tx.commit();
        session.close();
        sf.close();
    }
}