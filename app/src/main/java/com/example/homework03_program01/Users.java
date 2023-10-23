package com.example.homework03_program01;

import java.io.Serializable;

public class Users implements Serializable
{
    private String uname;
    private String pword;
    private String fname;
    private String lname;
    private String email;
    private String age;


    public Users(String uname, String pword, String fname, String lname, String email, String age)
    {
        this.uname = uname;
        this.pword = pword;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.age = age;
    }

    //Getters and Setters
    //(No setter for uname as it should not be changed later)
    public String getUname() {
        return uname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
