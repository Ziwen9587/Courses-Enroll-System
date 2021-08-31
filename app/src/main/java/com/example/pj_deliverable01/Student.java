package com.example.pj_deliverable01;

public class Student {
    private String ID;
    private String Name;
    private String PassWord;

    public Student(String id, String name, String password){
        ID = id;
        Name = name;
        PassWord = password;
    }

    public Student(){

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
}
