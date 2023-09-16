import java.io.*;
import java.util.*;

public class UniversityManagementSystem {

    /**
     * @param args the command line arguments
     */
    static Scanner input = new Scanner(System.in);
    static Scanner inputString = new Scanner(System.in);

    static final String adminUserName = "admin";
    static final String adminPassword = "admin123";

    public static University uni = new University();

    public static void main(String[] args) {
        // TODO code application logic here
        mainLoop();
    }

    public static void mainLoop(){
        while (true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t\tUniversity Management System\n\n\n");
            System.out.println("\t\t\t1. Sign In");
            System.out.println("\t\t\t2. Exit");
            System.out.println("\t\t------------------------------------------");
            int userinput = input.nextInt();
            if(userinput == 1)
                signIn();
            else if(userinput == 2){
                writeFiles("Students.ser");
                writeFiles("Courses.ser");
                writeFiles("Professors.ser");
                System.exit(0);
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static void writeFiles(String filename){
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            if(filename.equals("Students.ser"))
                out.writeObject(uni.getStudents());
            else if(filename.equals("Professors.ser"))
                out.writeObject(uni.getProfessors());
            else if(filename.equals("Courses.ser"))
                out.writeObject(uni.getCourses());
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void signIn(){
        while(true)
        {
            System.out.println("\t\t\t\tSign In\n");
            System.out.println("\t\t\t1. Student Sign In");
            System.out.println("\t\t\t2. Faculty Sign In");
            System.out.println("\t\t\t3. Admin Portal");
            System.out.println("\t\t\t4. Back");
            int userinput = input.nextInt();
            if(userinput == 1){
                String auth = signInForm("Student");
                if(!auth.equals("Invalid"))
                    studentPortal(auth);
            }
            else if(userinput == 2)
            {
                String auth = signInForm("Faculty");
                if(!auth.equals("Invalid"))
                    facultyPortal(auth);
            }
            else if(userinput == 3)
            {
                String auth = signInForm("Admin");
                adminPortal();
            }
            else if(userinput == 4)
                break;
            else
                System.out.println("Invalid Input!");
        }
    }

    public static String signInForm(String usertype){
        while(true){
            System.out.println("\tSign In Form");
            System.out.println("Enter Email:");
            String email = inputString.nextLine();
            System.out.println("Enter Password:");
            String password = inputString.nextLine();
            String auth = authentication(email, password, usertype);
            if(!auth.equals("Invalid"))
                return auth;
            System.out.println("Invalid Email or Password! Do you want to try again (y/n)");
            if(inputString.nextLine().equals("n"))
                return "Invalid";
        }

    }

    public static String authentication(String email, String password, String usertype){

        if(usertype.equals("Student")){
            for(int i=0; i<uni.getStudents().size();i++){
                if(email.equals(uni.getStudents().get(i).getEmail()) && password.equals(uni.getStudents().get(i).getPassword()))
                    return uni.getStudents().get(i).getEmail();
            }
        }
        else if(usertype.equals("Faculty")){
            for(int i=0; i<uni.getProfessors().size();i++){
                if(email.equals(uni.getProfessors().get(i).getEmail()) && password.equals(uni.getProfessors().get(i).getPassword()))
                    return uni.getProfessors().get(i).getEmail();
            }
        }
        else if(usertype.equals("Admin")){
            if(email.equals(adminUserName) && password.equals(adminPassword))
                return "admin";
        }
        return "Invalid";
    }

    public static void facultyPortal(String email){
        int i;
        for(i=0;i<uni.getProfessors().size();i++){
            if(uni.getProfessors().get(i).getEmail().equals(email))
                break;
        }
        Professor professor = uni.getProfessors().get(i);
        ArrayList<Course> courses = new ArrayList<>();
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t\tFaculty Portal\n");
            System.out.println("\t\t\t1. Add Marks\n");
            System.out.println("\t\t\t2. Back\n");
            int userin = input.nextInt();
            if(userin == 1){
                for(i=0;i<uni.getCourses().size();i++)
                {
                    if(uni.getCourses().get(i).getInstructor().getEmail().equals(professor.getEmail())){
                        courses.add(uni.getCourses().get(i));
                        System.out.println(uni.getCourses().get(i));
                    }
                }
                System.out.println("Select Course: ");
                int userinput = input.nextInt();
                addMarks(uni.getCourses().get(userinput));
            }
            else if(userin == 2)
                break;
        }
    }

    public static void addMarks(Course course){
        ArrayList<Student> students = new ArrayList<>();
        System.out.println("Students Enrolled: ");
        for(int i=0; i<uni.getStudents().size();i++)
        {
            for(int j=0;j<uni.getStudents().get(i).getCourses().size();j++){
                if(uni.getStudents().get(i).getCourses().get(j).getCode().equals(course.getCode())){
                    students.add(uni.getStudents().get(i));
                    System.out.println(uni.getStudents().get(i));
                    System.out.print("Enter GPA: ");
                    double gpa = input.nextDouble();
                    uni.getStudents().get(i).getCourses().get(j).setGpa(gpa);
                    break;
                }
            }
        }
    }

    public static void studentPortal(String email){
        int i;
        for(i=0;i<uni.getStudents().size();i++){
            if(uni.getStudents().get(i).getEmail().equals(email))
                break;
        }
        Student student = uni.getStudents().get(i);
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t\tStudent Portal\n");
            System.out.println("\t\t\t1. View Courses");
            System.out.println("\t\t\t2. Add Course");
            System.out.println("\t\t\t3. Drop Course");
            System.out.println("\t\t\t4. View Result");
            System.out.println("\t\t\t5. Back");
            int userinput = input.nextInt();
            if(userinput == 1){
                displayCourses(student);
            }
            else if(userinput == 2)
            {
                studentAddCourse(student);
            }
            else if(userinput == 3)
            {
                displayCourses(student);
                System.out.println("\nSelect the Course you want to Remove: ");
                int userin = input.nextInt();
                student.RemoveCourse(userin);

            }
            else if(userinput == 4){
                displayCourses(student);
                System.out.println("CGPA: "+student.calculateCgpa());
            }
            else if(userinput == 5)
            {
                break;
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static void studentAddCourse(Student student){
        display("Courses");
        System.out.println("\nSelect the Course you want to add: ");
        int userin = input.nextInt();
        Boolean flag = true;
        for(int i=0;i<student.getCourses().size();i++){
            if(student.getCourses().get(i).getCode().equals(uni.getCourses().get(i).getCode())){
                flag = false;
                break;
            }
        }
        if(flag){
            student.addCourse(uni.getCourses().get(userin));
        }
        else
            System.out.println("Course Already Added!");
    }

    public static void displayCourses(Student student){
        if(student.getCourses().isEmpty())
            System.out.println("No Courses to show!");
        else{
            for(int i=0;i<student.getCourses().size();i++){
                System.out.println(student.getCourses().get(i)+ " GPA: "+student.getCourses().get(i).getGpa());
            }
        }

    }

    public static void adminPortal(){
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t\tAdmin Portal\n");
            System.out.println("\t\t\t1. Student Management");
            System.out.println("\t\t\t2. Faculty Management");
            System.out.println("\t\t\t3. Course Management");
            System.out.println("\t\t\t4. Back");
            int userinput = input.nextInt();
            if(userinput == 1){
                adminStudent();
            }
            else if(userinput == 2)
            {
                adminFaculty();
            }
            else if(userinput == 3)
            {
                adminCourse();
            }
            else if(userinput == 4)
            {
                break;
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static void adminCourse(){
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t1. Display Courses");
            System.out.println("\t\t\t2. Add Course");
            System.out.println("\t\t\t3. Remove Course");
            System.out.println("\t\t\t4. Back");
            int userinput = input.nextInt();
            System.out.println("\t\t------------------------------------------\n");
            if(userinput == 1){
                display("Courses");
            }
            else if(userinput == 2)
            {
                addCourse();
            }
            else if(userinput == 3){
                display("Courses");
                System.out.println("Enter Course Number to Remove:");
                int userin = input.nextInt();
                uni.getCourses().remove(userin);
                System.out.println("Faculty Removed");
            }
            else if(userinput == 4)
            {
                break;
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static void adminFaculty(){
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t1. Display Faculty");
            System.out.println("\t\t\t2. Add Faculty");
            System.out.println("\t\t\t3. Remove Faculty");
            System.out.println("\t\t\t4. Back");
            int userinput = input.nextInt();
            System.out.println("\t\t------------------------------------------\n");
            if(userinput == 1){
                display("Faculty");
            }
            else if(userinput == 2)
            {
                addFaculty();
            }
            else if(userinput == 3){
                display("Faculty");
                System.out.println("Enter Faculty Number to Remove:");
                int userin = input.nextInt();
                uni.getProfessors().remove(userin);
                System.out.println("Faculty Removed");
            }
            else if(userinput == 4)
            {
                break;
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static Professor addFaculty(){
        System.out.println("Enter Name: ");
        String name = inputString.nextLine();
        System.out.println("Enter Email: ");
        String email = inputString.nextLine();
        System.out.println("Enter Password: ");
        String password = inputString.nextLine();
        System.out.println("Enter Salary: ");
        int salary = input.nextInt();
        Professor prof = new Professor(salary, name, email, password);
        uni.getProfessors().add(prof);
        return prof;
    }

    public static Course addCourse(){
        System.out.println("Enter Course Code: ");
        String code = inputString.nextLine();
        System.out.println("Enter Name: ");
        String name = inputString.nextLine();
        System.out.println("Enter Credit Hours: ");
        double credit = input.nextDouble();
        display("Faculty");
        System.out.println("Select Instructor: ");
        int instr = input.nextInt();
        Course course = new Course(code, name, credit, uni.getProfessors().get(instr));
        uni.getCourses().add(course);
        return course;
    }

    public static void adminStudent(){
        while(true){
            System.out.println("\t\t------------------------------------------");
            System.out.println("\t\t\t1. Display Students");
            System.out.println("\t\t\t2. Add Student");
            System.out.println("\t\t\t3. Remove Student");
            System.out.println("\t\t\t4. Back");
            int userinput = input.nextInt();
            System.out.println("\t\t------------------------------------------\n");
            if(userinput == 1){
                display("Student");
            }
            else if(userinput == 2)
            {
                addStudent();
            }
            else if(userinput == 3){
                display("Student");
                System.out.println("Enter Student Number to Remove:");
                int userin = input.nextInt();
                uni.getStudents().remove(userin);
                System.out.println("Student Removed");

            }
            else if(userinput == 4)
            {
                break;
            }
            else
                System.out.println("Invalid Input!");
        }
    }

    public static Student addStudent(){
        System.out.println("Enter Name: ");
        String name = inputString.nextLine();
        System.out.println("Enter Email: ");
        String email = inputString.nextLine();
        System.out.println("Enter Password: ");
        String password = inputString.nextLine();
        System.out.println("Enter Roll NO: ");
        int rollno = input.nextInt();
        System.out.println("Enter Semester: ");
        int sem = input.nextInt();
        Student student = new Student(rollno, sem, name, email, password);
        uni.getStudents().add(student);
        return student;
    }

    public static void display(String type){
        System.out.println("\n----------");
        System.out.println(type);
        System.out.println("----------\n");

        int size = 0;
        if(type.equals("Student"))
            size = uni.getStudents().size();
        else if(type.equals("Courses"))
            size = uni.getCourses().size();
        else if(type.equals("Faculty"))
            size = uni.getProfessors().size();


        if(type.equals("Student") && uni.getStudents().size()==0)
        {
            System.out.println("No Students to Show.");
        }
        else if(type.equals("Courses") && uni.getCourses().size()==0)
        {
            System.out.println("No Courses to Show.");
        }
        if(type.equals("Faculty") && uni.getProfessors().size()==0)
        {
            System.out.println("No Faculty to Show.");
        }
        else
        {
            for(int i =0; i<size;i++)
            {
                if(type.equals("Student"))
                    System.out.println(i+". "+uni.getStudents().get(i));
                else if(type.equals("Courses"))
                    System.out.println(i+". "+uni.getCourses().get(i));
                else if(type.equals("Faculty"))
                    System.out.println(i+". "+uni.getProfessors().get(i));
            }
        }
    }
}
