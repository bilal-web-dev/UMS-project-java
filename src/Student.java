/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;

public class Student extends Person implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    protected int rollNo;
    protected double cgpa;
    protected int semester;
    protected ArrayList<Course> courses;

    public Student(int rollNo, int semester, String name, String email, String password) {
        super(name, email, password);
        this.rollNo = rollNo;
        this.cgpa = 0.0;
        this.semester = semester;
        courses = new ArrayList<>();
    }

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    public void RemoveCourse(int index)
    {
        courses.remove(index);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public double calculateCgpa(){
        int totalcredithrs = 0;
        cgpa = 0;
        for(int i=0;i<courses.size();i++){
            cgpa += (courses.get(i).getGpa()*courses.get(i).getCredithrs());
            totalcredithrs += courses.get(i).getCredithrs();
        }
        cgpa /= totalcredithrs;
        return cgpa;
    }
    @Override
    public String toString() {
        return super.toString()+ "\t" + "RollNo: "+rollNo + "\t" + "CGPA: "+cgpa;
    }


}
