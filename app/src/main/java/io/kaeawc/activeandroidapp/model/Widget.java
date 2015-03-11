package io.kaeawc.activeandroidapp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Widget")
public class Widget extends Model {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "points")
    private long points;

    @Column(name = "birth_date")
    private Date birthDate;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public long getPoints() {
        return points;
    }
    public Date getBirthDate() {
        return birthDate;
    }

    public void setName(String value) {
        name = value;
    }
    public void setAge(int value) {
        age = value;
    }
    public void setPoints(long value) {
        points = value;
    }
    public void setBirthDate(Date value) {
        birthDate = value;
    }

}
