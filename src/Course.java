/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

public class Course implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    private String code;
    private String name;
    private double credithrs;
    private Professor instructor;
    private double gpa;

    public Course(String code, String name, double credithrs, Professor instructor) {
        this.code = code;
        this.name = name;
        this.credithrs = credithrs;
        this.instructor = instructor;
        this.gpa = 0;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredithrs() {
        return credithrs;
    }

    public void setCredithrs(double credithrs) {
        this.credithrs = credithrs;
    }

    public Professor getInstructor() {
        return instructor;
    }

    public void setInstructor(Professor instructor) {
        this.instructor = instructor;
    }

    public String toString(){
        return code + " " + name + " Credit Hours: " + credithrs + " Instructor: " + instructor.getName();
    }
}
