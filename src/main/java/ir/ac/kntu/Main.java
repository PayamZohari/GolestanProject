/**
 * Represents testing and log in (UX/UI PART)
 *
 * @author Pyam Zoahri
 * Warning !!!!!!!!this project hase not given to anybody and it hasn't token from any one!!!!!!
 * this Main class is for test
 * there is log in method which you can use project by running this class
 */
package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //testing my code and creating examples/////////////////////////////////////////////////////////////////////////
        Scanner input = new Scanner(System.in);
        System.out.println("*******************************************************************");
        System.out.println("************Welcome to GOLESTAN++ (author:MohammadPayamZohari)********");
        System.out.println("*******************************************************************");
        System.out.println("enter your user name: ");
        String mainUserName = input.nextLine();
        System.out.println("enter your password: ");
        String mainPassWord = input.nextLine();

        //////////////////////////////////////creating the main admin////////////////////////////////////////////////////
        Admin admin = new Admin("Payam", "Zohari",
                "1234567890", "PZ1380pzoh");
        ///////////////////////////example of students//////////////////////////////////////////////////////////////////
        Student s1 = new Student("Payam", "Zohari",
                "Computer", 123, "9936512340", "PyamZohari1",
                new HashMap<Lesson, Integer>(), new ArrayList<LessonsGroup>());
        Student s2 = new Student("Ali", "Ghyasi",
                "Mechanics", 128, "9936512341", "Aligh12345",
                new HashMap<Lesson, Integer>(), new ArrayList<LessonsGroup>());
        Student s3 = new Student("Negar", "Khodaii",
                "Mathematics", 109, "9936512342", "nKH123456",
                new HashMap<Lesson, Integer>(), new ArrayList<LessonsGroup>());
        Student s4 = new Student("Musa", "Naji",
                "Mechanics", 127, "9936512343", "musNaj1234",
                new HashMap<Lesson, Integer>(), new ArrayList<LessonsGroup>());
        Student s5 = new Student("Hoda", "Vahdati",
                "Physics", 111, "9936512344", "HVDT98765",
                new HashMap<Lesson, Integer>(), new ArrayList<LessonsGroup>());
        /////////////////////////example arrays of students/////////////////////////////////////////////////////////////
        ArrayList<Student> sArr1 = new ArrayList<>();
        ArrayList<Student> sArr2 = new ArrayList<>();
        sArr1.add(s1);
        sArr1.add(s2);
        sArr2.add(s3);
        sArr1.add(s3);
        sArr2.add(s4);
        sArr1.add(s5);
        ///////////////////////////////example times////////////////////////////////////////////////////////////////////
        Time t1 = new Time("MONDAY", 13, 45);
        Time t2 = new Time("TUESDAY", 12, 15);
        Time t3 = new Time("WEDNESDAY", 15, 25);
        Time t4 = new Time("THURSDAY", 17, 35);
        Time t5 = new Time("FRIDAY", 16, 55);
        //////////////////////////////////////////////adding time examples to arrays////////////////////////////////////
        ArrayList<Time> times = new ArrayList<>();
        times.add(t1);
        times.add(t2);
        ArrayList<Time> tProf = new ArrayList<>();
        tProf.add(t5);
        ////////////////////////////////example physical classes////////////////////////////////////////////////////////
        ClassRoom c1 = new ClassRoom("A12", 35);
        ClassRoom c2 = new ClassRoom("A13", 45);
        ClassRoom c3 = new ClassRoom("A15", 45);
        ClassRoom c4 = new ClassRoom("B12", 65);
        ClassRoom c5 = new ClassRoom("B13", 25);
        ClassRoom c6 = new ClassRoom("B14", 32);
        ///////////////////////////////////example of professors////////////////////////////////////////////////////////
        Professor p1 = new Professor("Mahdi", "Zamanian", "9934567890",
                "Zamanian12M", new ArrayList<LessonsGroup>());
        Professor p2 = new Professor("Roghaye", "Dust", "9934567891",
                "RD1234zzz", new ArrayList<LessonsGroup>());
        Professor p3 = new Professor("Rasul", "Dalir", "9934567892",
                "RasulD1234", new ArrayList<LessonsGroup>());
        Professor p4 = new Professor("Somayeh", "Moradi", "9934567893",
                "Smoradii12", new ArrayList<LessonsGroup>());
        Professor p5 = new Professor("Manuchehr", "Khasi", "9934567894",
                "Mkhasi9876", new ArrayList<LessonsGroup>());
        /////////////////////////////exaplme of arrays of professors////////////////////////////////////////////////////
        ArrayList<Professor> phds1 = new ArrayList<>();
        ArrayList<Professor> phds2 = new ArrayList<>();
        phds1.add(p1);
        phds1.add(p3);
        phds1.add(p5);
        phds2.add(p5);
        phds1.add(p2);
        phds2.add(p4);
        //////////////////////example lessons///////////////////////////////////////////////////////////////////////////
        Lesson l1 = new Lesson(131, "physics1", new ArrayList<Lesson>(),
                new ArrayList<Professor>(), 3);
        Lesson l2 = new Lesson(132, "riazi1", new ArrayList<Lesson>(),
                new ArrayList<Professor>(), 3);
        Lesson l3 = new Lesson(133, "mabaniBarnameNevisi", new ArrayList<Lesson>(),
                new ArrayList<Professor>(), 3);
        Lesson l4 = new Lesson(134, "andisheEslami",
                new ArrayList<Lesson>(), new ArrayList<Professor>(), 2);
        Lesson l5 = new Lesson(135, "farsi",
                new ArrayList<Lesson>(), new ArrayList<Professor>(), 3);
        ///////////////////////////////////////example of lesson groups/////////////////////////////////////////////////
        AttendanceLessonGroup lg1 = new AttendanceLessonGroup(99, "riazi1",
                new ArrayList<Student>(), times, 35, 4002, p1, c1);
        OnlineLessonGroup lg2 = new OnlineLessonGroup(93, "physics1",
                new ArrayList<Student>(), tProf, 35, 4002, p2);
        AttendanceLessonGroup lg3 = new AttendanceLessonGroup(94, "riazi1",
                new ArrayList<Student>(), tProf, 45, 4002, p5, c3);
        AttendanceLessonGroup lg4 = new AttendanceLessonGroup(95, "physics1",
                new ArrayList<Student>(), times, 65, 4002, p4, c4);
        OnlineLessonGroup lg5 = new OnlineLessonGroup(96, "mabaniBarnameNevisi",
                new ArrayList<Student>(), tProf, 55, 4002, p3);
        //////////////////////////////////////example of arrays of online and attendance lesson groups//////////////////
        ArrayList<LessonsGroup> lgArr1 = new ArrayList<>();
        ArrayList<LessonsGroup> lgArr2 = new ArrayList<>();
        lgArr1.add(lg1);
        lgArr2.add(lg2);
        lgArr2.add(lg4);
        //////////////////////////////////setting arrays of lesson groups to the professors/////////////////////////////
        p1.setLessonsGroups(lgArr1);
        p2.setLessonsGroups(lgArr2);
        p3.setLessonsGroups(lgArr2);
        p4.setLessonsGroups(lgArr2);
        p5.setLessonsGroups(lgArr1);
        /////////////////////////////setting arrays of students to lesson groups////////////////////////////////////////
        lg1.setStudents(sArr1);
        lg2.setStudents(sArr2);
        lg3.setStudents(sArr1);
        lg4.setStudents(sArr2);
        lg5.setStudents(sArr2);
        ///////////////////////////////////example of passed lessons hashmap///////////////////////////////////////////
        HashMap<Lesson, Integer> pl1 = new HashMap<>();
        HashMap<Lesson, Integer> pl2 = new HashMap<>();
        pl1.put(l1, 18);
        pl2.put(l2, 17);
        pl1.put(l3, 8);
        pl2.put(l4, 20);
        pl2.put(l5, 14);
        ///////////////////////////////setting passed lessons to some of students///////////////////////////////////////
        s1.setPassedLessons(pl1);
        s4.setPassedLessons(pl2);
        s5.setPassedLessons(pl2);
        ////////////////////////////////////////setting arrays of lesson groups to students/////////////////////////////
        s1.setLessonsGroup(lgArr1);
        s3.setLessonsGroup(lgArr2);
        ///////////////////////////////////adding students to grades list///////////////////////////////////////////////
        lg1.getStudnetGrades().put(s1, 0);
        lg1.getStudnetGrades().put(s2, 0);
        lg1.getStudnetGrades().put(s3, 0);
        lg1.getStudnetGrades().put(s5, 0);
        lg2.getStudnetGrades().put(s3, 0);
        lg4.getStudnetGrades().put(s3, 0);
        //////////////////////////////////send a request to admin///////////////////////////////////////////////////////
        Admin.getStudentsRequests().put(s1, lg1);
        /////////////////////////////////////Users Entry////////////////////////////////////////////////////////////////
        /**
         * log in (UX/UI) ;)
         */
        admin.userLogIn(mainUserName, mainPassWord, input);

        input.close();
    }
}