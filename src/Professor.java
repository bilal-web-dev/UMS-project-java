/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

public class Professor extends Person implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;

    protected double salary;

    public Professor(double salary, String name, String email, String password) {
        super(name, email, password);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString(){
        return super.toString()+ "\t" + "Salary: "+ salary;
    }
}
