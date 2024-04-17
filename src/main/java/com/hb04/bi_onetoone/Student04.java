package com.hb04.bi_onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student04 {
    @Id
    private int id;

    @Column(name="std_name")
    private String name;

    private int grade;

    //!!! sadece Diary tablosunda ilişki için kolon oluşturur
    // !!! set'leme islemi student isimli degiskene sahip olan Diary uzerinden yapilmali
    // !!! @OneToOne ilişkisi oluştururken mappedBy özelliğini kullanmazsanız, Hibernate
    // varsayılan davranışı kullanır. Bu durumda, ilişkinin iki tarafında ayrı ayrı
    // bir yabancı anahtar (foreign key) ve benzersiz sütunlar oluşturulur.
    @OneToOne(mappedBy = "student") //sadece günlük tarafında student i set et, iki tarafta da set etme
    private Diary04 diary;          // günlük tarafına diary1.setStudent(student1) yazacağız...

    //getter-setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Diary04 getDiary() {
        return diary;
    }

    public void setDiary(Diary04 diary) {
        this.diary = diary;
    }
    /// toString

    @Override
    public String toString() {
        return "Student04{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                //", diary=" + diary +  ==> hem student de diary, hem de diary de student olursa sonsuz döngüye girer, birini kaldırmamız lazım...
                '}';
    }
}
