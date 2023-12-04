package org.example;

import javax.persistence.*;
import java.util.Date;


@Entity
@Embeddable
public class Student {
    private String student_name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;

    private String mobile_number;
    private Date date_time;

    public Student(String student_name, int student_id, Date date_time) {
        this.student_name = student_name;
        this.student_id = student_id;
        this.date_time = date_time;

    }
    public  Student(){
        super();
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate(Date date) {
        this.date_time = date;
    }
    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }


}
