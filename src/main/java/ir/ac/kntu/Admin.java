/**
 * Represents admins class
 *
 * @author Payam Zohari
 * this class is a model for admins of golestan who has access to every users options and also it has it's own options
 * admin is also derivded class from user because admin is also a user who it's usage is managing the system
 * most of this project's work is on this class and we test it as admin
 */
package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * admin is also a user so he/she has first and last name, a username and a password to log in
 * there is also a list of admins
 */
public class Admin extends User {
    private static HashMap<Student, LessonsGroup> studentsRequests = new HashMap<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private String adminUserName;
    private String adminPassword;

    public Admin(String fistName, String lastName, String adminUserName, String adminPassword) {
        super("Golestan", "Admin", adminUserName, adminPassword);
        this.setFirstName(fistName);
        this.setLastName(lastName);
        this.setUserName(adminUserName);
        this.setPassword(adminPassword);
        admins.add(this);
        User.getUsers().add(this);
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public static HashMap<Student, LessonsGroup> getStudentsRequests() {
        return studentsRequests;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        Admin.admins = admins;
    }

    public static void setStudentsRequests(HashMap<Student, LessonsGroup> studentsRequests) {
        Admin.studentsRequests = studentsRequests;
    }

    /**
     * whole project is based on this method
     * it contains menus for admins
     * menu has 9 option , such as adding student , edit(add/remove lesson group) it, add professor and editting it
     * also add a lesson group by the professor we create
     * admin can add a whole new lesson
     * he/she can create a field through creating new student
     * this method offers changing admins pasword, add new admin and also having a report of list of classes
     * users such as students and so forth
     */
    public void logIn(Scanner input) {
        boolean stayLogIn = true;
        while (stayLogIn == true) {
            /////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("choose from the options below and then enter it's number");
            System.out.println("0-Add new student");
            System.out.println("1-Add new Professor");
            System.out.println("2-Add new lesson/class");
            System.out.println("3-(edit student)add/remove lesson for student");
            System.out.println("4-(edit professor)add/remove lesson for professor");
            System.out.println("5-add new Admin");
            System.out.println("6-change password");
            System.out.println("7-print users/classes and so forth");
            System.out.println("8-check and answer students requests");
            System.out.println("9-log out");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 0:
                    Student.addStudnet(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 1:
                    Professor.addProfessor(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 2:
                    Lesson.addLesson(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    Student.editStudent(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 4:
                    Professor.editProfessor(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 5:
                    Admin.addAdmin(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 6:
                    System.out.println("enter your new password: ");
                    String newAdminsPassword = input.nextLine();
                    this.setAdminPassword(newAdminsPassword);
                    System.out.println("your password has just edited");
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 7:
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    System.out.println("choose what do you want to get print from:");
                    System.out.println("0-print of students");
                    System.out.println("1-print of professors");
                    System.out.println("2-print of lessons");
                    System.out.println("3-print of lesson groups");
                    System.out.println("4-print of university fields");
                    System.out.println("5-print of whole users");
                    int choice2 = Integer.parseInt(input.nextLine());
                    switch (choice2) {
                        case 0:
                            for (int i = 0; i < Student.getStudents().size(); i++) {
                                System.out.println(Student.getStudents().get(i).toString());
                            }
                            break;
                        case 1:
                            for (int i = 0; i < Professor.getProfessors().size(); i++) {
                                System.out.println(Professor.getProfessors().get(i).toString());
                            }
                            break;
                        case 2:
                            for (int i = 0; i < Lesson.getLessons().size(); i++) {
                                if ((Lesson.getLessons().get(i) instanceof LessonsGroup) == false) {
                                    System.out.println(((Lesson) Lesson.getLessons().get(i)).toString());
                                }
                            }
                            break;
                        case 3:
                            for (int i = 0; i < LessonsGroup.getLessonsGroups().size(); i++) {
                                System.out.println(LessonsGroup.getLessonsGroups().get(i).toString());
                            }
                            break;
                        case 4:
                            for (int i = 0; i < Fields.getUniversityFields().size(); i++) {
                                System.out.println(Fields.getUniversityFields().get(i).toString());
                            }
                            break;
                        case 5:
                            for (int i = 0; i < User.getUsers().size(); i++) {
                                System.out.println(((User) User.getUsers().get(i)).toString());
                            }
                            break;
                        default:
                            System.out.println("invalid input");
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                case 8:
                    this.checkRemovingRequests(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 9:
                    System.out.println("GoodLuck " + this.getClass() + " " +
                            this.getFirstName() + " " + this.getLastName());
                    stayLogIn = false;
                    break;
                default:
                    System.out.println("invalid input");
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminFirstName='" + this.getFirstName() + '\'' +
                "adminLastName='" + this.getLastName() + '\'' +
                "adminUserName='" + "xxxxxxxxxx" + '\'' +
                ", adminPassword='" + "xxxxxxxxxxxx" + '\'' +
                '}';
    }

    public static void addAdmin(Scanner input) {
        String resume9 = "";
        do {
            System.out.println("adding new Admin");
            System.out.println("enter first name: ");
            String newAdminfName = input.nextLine();
            System.out.println("enter last name: ");
            String newAdminlName = input.nextLine();
            System.out.println("enter user name: ");
            String newAdminuName = input.nextLine();
            System.out.println("enter password : ");
            String newAdminPass = input.nextLine();
            Admin newAdmin = new Admin(newAdminfName, newAdminlName, newAdminuName, newAdminPass);
            System.out.println("do you wanna add more ?(Y/N)");
            resume9 = input.nextLine();
        } while (resume9.equals("Y"));
        System.out.println("admin(s) has just added");
    }

    public void checkRemovingRequests(Scanner input) {
        if (studentsRequests.size() != 0) {
            for (Map.Entry<Student, LessonsGroup> request : studentsRequests.entrySet()) {
                System.out.println(request.getKey().toString() + " has sent request for removing " +
                        request.getValue().getSimpleName());

                boolean answered = false;
                do {
                    if (studentsRequests.size() != 0 && studentsRequests != null) {
                        System.out.println("do you want to accept this request?(Y/N)");
                        String answerToRequest = input.nextLine();
                        if (answerToRequest.equals("Y")) {
                            request.getKey().removeLessonGroup(request.getValue());
                            request.getKey().getRequestsResults().put(request.getValue(), true);
                            answered = true;
                        } else if (answerToRequest.equals("N")) {
                            request.getKey().getRequestsResults().put(request.getValue(), false);
                            answered = true;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else {
                        System.out.println("there are no request anymore");
                        break;
                    }
                } while (!answered);

                request.setValue(null);
                System.out.println("do you want to answer to the next requests?(Y/N)");
                String keepAnswering = input.nextLine();
                if (keepAnswering.equals("Y")) {
                    if (studentsRequests.size() <= 1 || studentsRequests == null) {
                        System.out.println("there are no more requests");
                        break;
                    }
                } else if (keepAnswering.equals("N")) {
                    break;
                } else if (!keepAnswering.equals("Y")) {
                    System.out.println("invalid input");
                }
            }

            for (Map.Entry<Student, LessonsGroup> removeAnsweredRequests : studentsRequests.entrySet()) {
                if (removeAnsweredRequests.getValue() == null) {
                    studentsRequests.remove(removeAnsweredRequests.getKey());
                }
            }
        } else {
            System.out.println("there are no request");
        }

    }


}






