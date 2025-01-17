package com.hb01.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RunnerFetch01 {
    public static void main(String[] args) {
        Configuration con = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student01.class);

        SessionFactory sf= con.buildSessionFactory();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();

        //!!! DB den bilgi çekmek için 3 yol var.
            //1- get
            //2-SQL
            //3-HQL

        //1- Get() methodu  ********************************
        System.out.println("---- GET ile  ----------------");
        Student01 student1=session.get(Student01.class,1001);
        System.out.println(student1);

        Student01 student2=session.get(Student01.class,1002);
        System.out.println(student2);

        //2. yol SQL ile
        System.out.println("---- SQL ile  ----------------");
        String sqlQuery="Select * From t_student01";
        List<Objects[]> resultList1 = session.createSQLQuery(sqlQuery).getResultList();
        for(Object[] object:resultList1){
            System.out.println(Arrays.toString(object));
        }

        //3. yol HQL ile
        System.out.println("---- HQL ile  ----------------");
        String hqlQuery = "From Student01";
        // burada HQL java dilinde konuşu bu sebeple class ismini yazdık
        List<Student01> resultList2 = session.createQuery(hqlQuery,Student01.class).getResultList();
        for (Student01 student01:resultList2){
            System.out.println();
        }

        // *** NOT *** SQL kullanınca String deki hataları vermez, her an patlayabilir ama HQL java ca olduğundan hataları verir


        // Excercises
        // ismi .... olan kişiyi getir
        System.out.println("Uygulamalar- belirli ismi getir");
        String sqlQuery2= "SELECT * FROM t_student01 WHERE student_name= 'Dogu Bey'";
        Object[] uniqueResult1 = (Object[]) session.createSQLQuery(sqlQuery2).uniqueResult();
        System.out.println(Arrays.toString(uniqueResult1));

        System.out.println(uniqueResult1[0]+" : "+uniqueResult1[1]+" : "+uniqueResult1[2]);

        // aynı sorguyu HQL ile yapalım
        String hqlQuery2 = "From Student01 Where name='Dogu Bey'";
        Student01 uniqueResult2 = session.createQuery(hqlQuery2, Student01.class).uniqueResult();
        System.out.println(uniqueResult2.getName());
        //uniqueResul2 den sonra . ile hangi alanı yazdıracağımızı seçebiliriz.




        //HQL-> grade değeri 70 olan öğrencileri getir...
        System.out.println("---- HQL ile grade=70 sonucunu getir  ----------------");

        String hqlQuery3 = "SELECT s.id,s.name FROM Student01 s WHERE s.grade=70";
        List<Object[]> resultList3 = session.createQuery(hqlQuery3).getResultList();
        for (Object[] objects : resultList3){
            System.out.println(Arrays.toString(objects));
        }

        //id'ye göre azalan sıralı listeyi HQL ile yapalım
        System.out.println("---- HQL ile grade=70 sonucunu getir  ----------------");
        String hqlQuery4 = "FROM Student01 s ORDER BY s.id DESC";
        List<Student01> resultList4 = session.createQuery(hqlQuery4, Student01.class).getResultList();
        for(Student01 student01 : resultList4){
            System.out.println(student01);
        }




        tx.commit();
        session.close();
        sf.close();


        /*
         // !!! get - SQL - HQL Hangisini tercih etmem gerekir ?
        /*
                1. get
                2. HQL
                3. SQL

                        *   Native SQL hizli olsada eksi yonleri :
                1) bazi DB'lerde sql syntax'in farkliliklar olabiliyor, Native SQL burada sorun olabilir
                2) String yapilarin hataya acik olmasi
         */

    }
}
