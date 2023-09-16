
import java.util.*;
import java.io.*;

public class University {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Professor> professors;

    University()
    {
        readData();
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    public void addProfessor(Professor professor)
    {
        professors.add(professor);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public void readData(){
        ArrayList<Student> objectsList1 = new ArrayList<>();
        ArrayList<Professor> objectsList2 = new ArrayList<>();
        ArrayList<Course> objectsList3 = new ArrayList<>();

        try{
            FileInputStream fis = new FileInputStream("Students.ser");
            ObjectInputStream input = new ObjectInputStream(fis);
            objectsList1 = (ArrayList<Student>)input.readObject();
            students = objectsList1;

            fis = new FileInputStream("Professors.ser");
            input = new ObjectInputStream(fis);
            objectsList2 = (ArrayList<Professor>)input.readObject();
            professors = objectsList2;

            fis = new FileInputStream("Courses.ser");
            input = new ObjectInputStream(fis);
            objectsList3 = (ArrayList<Course>)input.readObject();
            courses = objectsList3;
        }catch(Exception e){
            System.out.println(e);
        }

    }


}
