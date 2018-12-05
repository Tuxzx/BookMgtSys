package com.tuxzx.domain;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String tel;
    private String pro;
    private Date inTime;

    public Student() {
    }

    public Student(String id, String name, String gender, int age, String tel, String pro, Date inTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.tel = tel;
        this.pro = pro;
        this.inTime = inTime;
    }

    @Override
    public String toString() {
        return "id: "+id+"\nname: "+name+"\ngender: "+gender+"\nage: "+age+"\ntel: "+tel+"\npro: "+pro+"\ninTime: "+inTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
}
