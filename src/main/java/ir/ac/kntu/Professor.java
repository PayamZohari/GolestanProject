/**
 * Represents the class for professors in golestan
 *
 * @author Payam Zohari
 * this is class for professors who teach in ths university as it may sounds obvious
 * it is a subclass for user class and inherites from that classes
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * field for each professor , besides users field(parent) are their number which is also their username
 * also the list of lessons which they are gonna teach in following term
 */
public class Professor extends User {
    private static ArrayList<Professor> professors = new ArrayList<>();
    //non-static fields
    private String professorNumber = "xxxxxxxxxx";
    private ArrayList<LessonsGroup> lessonsGroups = new ArrayList<>();

    //constructor
    public Professor(String firstName, String lastName, String professorNumber,
                     String professorPassword, ArrayList<LessonsGroup> lessonsGroups) {
        super(firstName, lastName, professorNumber, professorPassword);

        String proPattern = "[0-9]{10}";
        Pattern proPt = Pattern.compile(proPattern);
        Matcher proMt = proPt.matcher(professorNumber);
        boolean proCheck = proMt.matches();
        if (proCheck == true) {
            this.professorNumber = professorNumber;
        }
        if (lessonsGroups.size() != 0) {
            this.lessonsGroups = new ArrayList<LessonsGroup>(lessonsGroups);
        } else {
            this.lessonsGroups = new ArrayList<>();
        }

        professors.add(this);
        User.getUsers().add(this);
    }
    //getters

    public static void setProfessors(ArrayList<Professor> professors) {
        Professor.professors = professors;
    }

    public String getProfessorNumber() {
        return professorNumber;
    }

    public ArrayList<LessonsGroup> getLessonsGroups() {
        return lessonsGroups;
    }

    public static ArrayList<Professor> getProfessors() {
        return professors;
    }

    //setters


    public void setProfessorNumber(String professorNumber) {
        this.professorNumber = professorNumber;
    }

    public void setLessonsGroups(ArrayList<LessonsGroup> lessonsGroups) {
        this.lessonsGroups = lessonsGroups;
    }


    //overrided methods

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Professor professor = (Professor) o;
        return Objects.equals(professorNumber, professor.professorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorNumber);
    }

    //log-In Method

    /**
     * log in method for professors contains the menu for them for checking their classes in this term and changing
     * theri password or log out
     */
    public void logIn(Scanner input) {
        boolean stayLogIn = true;
        while (stayLogIn == true) {
            System.out.println("choose from the options below and then enter it's number");
            System.out.println("0-show current term's classes");
            System.out.println("1-change password");
            System.out.println("2-student list of this term's lesson groups");
            System.out.println("3-token lesson groups in this term (weekly sorted : NEW FEATURE!!)");
            System.out.println("4-check or enter this term's lessons group's student's gardes");
            System.out.println("9-log out");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 0:
                    for (int i = 0; i < this.lessonsGroups.size(); i++) {
                        if (lessonsGroups != null) {
                            System.out.println(this.lessonsGroups.get(i).toString());
                        }
                    }
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 1:
                    System.out.println("enter your new password");
                    String newPassword = input.nextLine();
                    String passwordPattern1 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,12})";
                    String passwordPattern2 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*<>.,/?]).{8,12})";
                    Pattern pt41 = Pattern.compile(passwordPattern1);
                    Pattern pt42 = Pattern.compile(passwordPattern2);
                    Matcher mt41 = pt41.matcher(newPassword);
                    Matcher mt42 = pt42.matcher(newPassword);
                    boolean studentPasswordCheck = (mt41.matches() || mt42.matches());
                    if (studentPasswordCheck == true) {
                        this.setPassword(newPassword);
                    } else {
                        System.out.println("invalid input");
                    }
                    break;
                ///////////////////////////////////////////////////////////////////////////////////////////////////
                case 2:
                    boolean finished = false;
                    do {
                        System.out.println("choose which lesson group's student's list do you want");
                        if (this.lessonsGroups.size() != 0 && this.lessonsGroups != null) {
                            for (int i = 0; i < this.lessonsGroups.size(); i++) {
                                System.out.println((i + 1) + "-" + this.lessonsGroups.get(i));
                            }
                            int chooseStudList = Integer.parseInt(input.nextLine());
                            chooseStudList--;
                            for (int i = 0; i < this.lessonsGroups.get(chooseStudList).getStudents().size(); i++) {
                                System.out.println(this.lessonsGroups.get(chooseStudList).getStudents().get(i).toString());
                            }

                            System.out.println("do you want to see others lists?(Y/N)");
                            String keepSeeLists = input.nextLine();
                            if (keepSeeLists.equals("N")) {
                                finished = true;
                                break;
                            } else if (keepSeeLists.equals("Y")) {
                                System.out.println("invalid input");
                            }
                        } else {
                            System.out.println("you haven't token any lesson group in this term yet");
                            finished = true;
                        }
                    } while (!finished);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    ArrayList<LessonsGroup> mondayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> tuesdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> wednesdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> thursdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> fridayLessons = new ArrayList<>();

                    for (LessonsGroup professorLessonGroup : this.getLessonsGroups()) {
                        for (Time lessonGroupTimes : professorLessonGroup.getGroupLessonsTime()) {
                            switch (lessonGroupTimes.getWeekDays()) {
                                case MONDAY:
                                    mondayLessons.add(professorLessonGroup);
                                    break;
                                case TUESDAY:
                                    tuesdayLessons.add(professorLessonGroup);
                                    break;
                                case WEDNESDAY:
                                    wednesdayLessons.add(professorLessonGroup);
                                    break;
                                case THURSDAY:
                                    thursdayLessons.add(professorLessonGroup);
                                    break;
                                case FRIDAY:
                                    fridayLessons.add(professorLessonGroup);
                                    break;
                                default:
                                    System.out.println("there are no classes on the weekends");
                            }
                        }
                    }

                    System.out.print("monday :   ");
                    if (mondayLessons != null && mondayLessons.size() > 0) {
                        for (LessonsGroup lessonsGroup : mondayLessons) {
                            System.out.print(lessonsGroup.getSimpleName() + "  ");
                        }
                    }

                    System.out.print("tuesday :   ");
                    if (tuesdayLessons != null && tuesdayLessons.size() > 0) {
                        for (LessonsGroup lessonsGroup : tuesdayLessons) {
                            System.out.print(lessonsGroup.getSimpleName() + "  ");
                        }
                    }

                    System.out.print("wednesday :   ");
                    if (wednesdayLessons != null && wednesdayLessons.size() > 0) {
                        for (LessonsGroup lessonsGroup : wednesdayLessons) {
                            System.out.print(lessonsGroup.getSimpleName() + "  ");
                        }
                    }

                    System.out.print("thursday :   ");
                    if (thursdayLessons != null && thursdayLessons.size() > 0) {
                        for (LessonsGroup lessonsGroup : thursdayLessons) {
                            System.out.print(lessonsGroup.getSimpleName() + "  ");
                        }
                    }

                    System.out.print("friday :   ");
                    if (fridayLessons != null && fridayLessons.size() > 0) {
                        for (LessonsGroup lessonsGroup : fridayLessons) {
                            System.out.print(lessonsGroup.getSimpleName() + "  ");
                        }
                    }
                    System.out.println();
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 4:
                    System.out.println("choose what do you want to do with grades");
                    System.out.println("0-enter/edit student grades of your lesson group");
                    System.out.println("1-just check whole student's gradex of one of your lesson groups");
                    int choose44 = Integer.parseInt(input.nextLine());
                    boolean finished2 = false;
                    do {
                        System.out.println("choose which lesson group's student's list do you want");
                        if (this.lessonsGroups.size() != 0 && this.lessonsGroups != null) {
                            for (int i = 0; i < this.lessonsGroups.size(); i++) {
                                System.out.println((i + 1) + "-" + this.lessonsGroups.get(i));
                            }
                            int chooseStudList2 = Integer.parseInt(input.nextLine());
                            chooseStudList2--;

                            if (choose44 == 0) {
                                if (this.lessonsGroups.get(chooseStudList2) instanceof OnlineLessonGroup) {
                                    this.enterLessonGroupGrades((OnlineLessonGroup) (this.lessonsGroups.
                                            get(chooseStudList2)), input);
                                } else {
                                    this.enterLessonGroupGrades((AttendanceLessonGroup) (this.lessonsGroups.
                                            get(chooseStudList2)), input);
                                }
                            } else if (choose44 == 1) {
                                this.checkWholeStudentsGrades(this.lessonsGroups.get(chooseStudList2));
                            } else {
                                System.out.println("invalid input");
                            }

                            System.out.println("do you want to check/enter the grades of other lessons groups?(Y/N)");
                            String keepEnterGrades = input.nextLine();
                            if (keepEnterGrades.equals("N")) {
                                break;
                            } else if (!keepEnterGrades.equals("Y")) {
                                System.out.println("invalid input");
                            }
                        } else {
                            System.out.println("you haven't token any lesson group till now in this term");
                            finished2 = true;
                        }
                    } while (!finished2);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 9:
                    System.out.println("GoodLuck " + this.getClass() + " " + this.getFirstName()
                            + " " + this.getLastName());
                    stayLogIn = false;
                    break;

                default:
                    System.out.println("invalid input");
            }
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
                "professorNumber='" + professorNumber + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }

    /**
     * removes a lesson if it is possible
     * this also removes it for students who has token this class since if there is no teacher there wont be any class
     *
     * @param x x is the number of lesson user wants to delete based on the menu which has shown to him/her
     */
    public void removeLesson(int x) {
        if (this.lessonsGroups.size() > 0 && x < this.lessonsGroups.size() && x >= 0) {
            int check = this.lessonsGroups.size();

            if (this.lessonsGroups.get(x) instanceof AttendanceLessonGroup) {
                AttendanceLessonGroup tempAttendance = (AttendanceLessonGroup) this.lessonsGroups.get(x);

                for (ClassRoom classRoom : ClassRoom.getClassRooms()) {
                    if (classRoom.equals(tempAttendance)) {
                        ClassRoom.getClassRooms().remove(classRoom);
                    }
                }
            }
            this.lessonsGroups.remove(x);
        } else {
            System.out.println("failed out of bound");
        }
    }

    public static void addProfessor(Scanner input) {
        int container = 0;
        ArrayList<LessonsGroup> pTempLessonsGroup = new ArrayList<>();
        Professor p = new Professor("Rasul", "Dalir", "1234341278",
                "Rasulfard1", pTempLessonsGroup);
        System.out.println("first name:");
        String pFirstName = input.nextLine();
        System.out.println("last name:");
        String pLastName = input.nextLine();
        System.out.println("user name:");
        String pUserName = input.nextLine();
        System.out.println("password:");
        String pPassword = input.nextLine();
        String resume3 = "";
        do {
            System.out.println("choose the number of lesson from list below");
            for (int i = 0; i < Lesson.getLessons().size(); i++) {
                if ((Lesson.getLessons().get(i) instanceof LessonsGroup) == false) {
                    System.out.println((i + 1) + ((Lesson) Lesson.getLessons().get(i)).toString());
                }
            }
            int chooseLesson = Integer.parseInt(input.nextLine());
            chooseLesson--;
            container = chooseLesson;

            //this part from last version was meaningless since a professor can take more than one lesson group
            //from one same lesson
            /*
            boolean isUnique = true;
            for (int i = 0; i < pTempLessonsGroup.size(); i++) {
                if (Lesson.getLessons().get(chooseLesson).
                        equals(pTempLessonsGroup.get(i)) == true) {
                    isUnique = false;
                    break;
                }
            }
            */

            System.out.println("add the group class code: ");
            int pGroupLessonCode = Integer.parseInt(input.nextLine());
            System.out.println("add your class capacity: ");
            int pCapacity = Integer.parseInt(input.nextLine());
            System.out.println("add term number: ");
            int pTermNumber = Integer.parseInt(input.nextLine());
            System.out.println("your class time:");
            System.out.println("how many times a week?");
            int timesAweek = Integer.parseInt(input.nextLine());
            ArrayList<Time> pTimes = new ArrayList<>(timesAweek);
            for (int j = 0; j < timesAweek; j++) {
                System.out.println("day:");
                String day = input.nextLine();
                System.out.println("hour:");
                int h = Integer.parseInt(input.nextLine());
                System.out.println("minute");
                int m = Integer.parseInt(input.nextLine());
                pTimes.add(new Time(day, h, m));
            }
            System.out.println("how this class is going to be held? online(enter0)or" +
                    "in-person(enter1)?");
            int onlineOrAttendance = Integer.parseInt(input.nextLine());
            ArrayList<Student> pStuds = new ArrayList<>();
            LessonsGroup pLessonsGroup = new LessonsGroup(0, 0, new ArrayList<Student>(),
                    new ArrayList<Time>(), 0, 0, new Professor("", "",
                    "", "",
                    new ArrayList<LessonsGroup>()));
            if (onlineOrAttendance == 0) {
                OnlineLessonGroup onlineLessonGroup = new OnlineLessonGroup(pGroupLessonCode,
                        Lesson.getLessons().get(chooseLesson).getLessonName(), pStuds,
                        pTimes, pCapacity, pTermNumber, p);
                pLessonsGroup = onlineLessonGroup;
            } else if (onlineOrAttendance == 1) {
                System.out.println("you should choose this lesson group class room");
                System.out.println("chooose the classroom between these classrooms below");
                for (int i = 0; i < ClassRoom.getClassRooms().size(); i++) {
                    System.out.println((i + 1) + "-" + ClassRoom.getClassRooms().get(i).toString());
                }
                int chooseClassRoom = Integer.parseInt(input.nextLine());
                AttendanceLessonGroup attendanceLessonGroup = new AttendanceLessonGroup(pGroupLessonCode,
                        Lesson.getLessons().get(chooseLesson).getLessonName(), pStuds,
                        pTimes, pCapacity, pTermNumber, p, ClassRoom.getClassRooms().get(chooseClassRoom));
                pLessonsGroup = attendanceLessonGroup;
            }
            ////////////////////////in professor's lesson group enrolle
            pLessonsGroup.enrolle(pTempLessonsGroup);
            //////////////////////////////////////////////////////////////////
            System.out.println("do you wanna add more ?(Y/N)");
            resume3 = input.nextLine();
        } while (resume3.equals("Y"));
        p = new Professor(pFirstName, pLastName, pUserName, pPassword, pTempLessonsGroup);

        for (int i = 0; i < pTempLessonsGroup.size(); i++) {
            pTempLessonsGroup.get(i).setLessonGroupProfessor(p);
        }
        Lesson.getLessons().get(container).getProfessors().add(p);
        System.out.println("professor has just Added");
    }

    public static void editProfessor(Scanner input) {
        System.out.println("choose the professor from list below: ");
        for (int i = 0; i < Professor.getProfessors().size(); i++) {
            System.out.println((i + 1) + "-" + Professor.getProfessors().get(i).toString());
        }
        int chooseProf = Integer.parseInt(input.nextLine());
        chooseProf--;
        System.out.println("choose what do you want:");
        System.out.println("0-add a new lesson's group from lessons from excisting lessons ");
        System.out.println("1-remove a lesson's group from this professor list");
        int chooseEditProf = Integer.parseInt(input.nextLine());
        if (chooseEditProf == 0) {
            Professor.getProfessors().get(chooseProf).addLessonToProfessor(input);
        } else if (chooseEditProf == 1) {
            Professor.getProfessors().get(chooseProf).removeLessonFromProfessor(input);
        } else {
            System.out.println("invalid input");
        }
    }

    public void addLessonToProfessor(Scanner input) {
        String resume7 = "";
        do {
            System.out.println("choose the number of lesson from list below");
            for (int i = 0; i < Lesson.getLessons().size(); i++) {
                if ((Lesson.getLessons().get(i) instanceof LessonsGroup) == false) {
                    System.out.println((i + 1) + ((Lesson) Lesson.getLessons().
                            get(i)).toString());
                }
            }
            int chooseLesson4 = Integer.parseInt(input.nextLine());
            chooseLesson4--;
            ArrayList<LessonsGroup> profLgTemp = this.getLessonsGroups();
            boolean isUnique2 = true;
            for (int i = 0; i < profLgTemp.size(); i++) {
                if (Lesson.getLessons().get(chooseLesson4).equals((Lesson) profLgTemp.get(i)) == true) {
                    isUnique2 = false;
                    break;
                }
            }
            System.out.println("add the group class code: ");
            int pGroupLessonCode1 = Integer.parseInt(input.nextLine());
            System.out.println("add your class capacity: ");
            int pCapacity1 = Integer.parseInt(input.nextLine());
            System.out.println("add term number: ");
            int pTermNumber1 = Integer.parseInt(input.nextLine());
            System.out.println("your class time:");
            System.out.println("how many times a week?");
            int timesAweek1 = Integer.parseInt(input.nextLine());
            ArrayList<Time> pTimes1 = new ArrayList<>(timesAweek1);
            for (int j = 0; j < timesAweek1; j++) {
                System.out.println("day:");
                String day1 = input.nextLine();
                System.out.println("hour:");
                int h1 = Integer.parseInt(input.nextLine());
                System.out.println("minute");
                int m1 = Integer.parseInt(input.nextLine());
                pTimes1.add(new Time(day1, h1, m1));
            }
            ArrayList<LessonsGroup> tempTest = new ArrayList<>();
            Professor p1 = new Professor("Roghaye", "Dust", "1345454512",
                    "Rdust12345", tempTest);
            ArrayList<Student> pStuds1 = new ArrayList<>();
            System.out.println("how this class is going to be held? online(enter0)or" +
                    "in-person(enter1)?");
            int onlineOrAttendance22 = Integer.parseInt(input.nextLine());
            ArrayList<Student> pStuds22 = new ArrayList<>();
            LessonsGroup pLessonsGroup22 = new LessonsGroup(0, 0, new ArrayList<>(),
                    new ArrayList<>(), 0, 0, new Professor(""
                    , "", "",
                    "", new ArrayList<>()));
            if (onlineOrAttendance22 == 0) {
                OnlineLessonGroup onlineLessonGroup22 = new OnlineLessonGroup(pGroupLessonCode1, Lesson.getLessons().
                        get(chooseLesson4).getLessonName(), pStuds1, pTimes1,
                        pCapacity1, pTermNumber1, p1);
                pLessonsGroup22 = onlineLessonGroup22;
            } else if (onlineOrAttendance22 == 1) {
                System.out.println("you should choose this lesson group class room");
                System.out.println("chooose the classroom between these classrooms below");
                for (int i = 0; i < ClassRoom.getClassRooms().size(); i++) {
                    System.out.println((i + 1) + "-" + ClassRoom.getClassRooms().get(i).toString());
                }
                int chooseClassRoom22 = Integer.parseInt(input.nextLine());
                AttendanceLessonGroup attendanceLessonGroup22 = new AttendanceLessonGroup(pGroupLessonCode1,
                        Lesson.getLessons().get(chooseLesson4).getLessonName(), pStuds1, pTimes1,
                        pCapacity1, pTermNumber1, p1, ClassRoom.getClassRooms().get(chooseClassRoom22));
                pLessonsGroup22 = attendanceLessonGroup22;
            }
          /*
            boolean pIntercurrent1 = false;
            if (this.getLessonsGroups().size() != 0) {
                for (int k = 0; k < pTimes1.size(); k++) {
                    if ((pLessonsGroup22.checkIntercurrentLessonGroup(this.getLessonsGroups()) == true)) {
                        pIntercurrent1 = true;
                        break;
                    }
                }
            }
            if (pIntercurrent1 == false) {
                this.getLessonsGroups().add(pLessonsGroup22);
                System.out.println("the lesson group has jus been added to the list successfuly");
            } else {
                LessonsGroup.getLessonsGroups().remove(pLessonsGroup22);
                System.out.println("you cannot take this class because it" +
                        " hase intercurrent with other classes of your's");
            }
            */
            pLessonsGroup22.enrolle(this.lessonsGroups);

            System.out.println("do you wanna add more ?(Y/N)");
            resume7 = input.nextLine();
        } while (resume7.equals("Y"));
    }

    public void removeLessonFromProfessor(Scanner input) {
        String resume8 = "";
        do {
            if (this.getLessonsGroups().size() > 0) {
                System.out.println("choose the lesson group you wanna remove " +
                        "from this professor's lessons");
                for (int i = 0; i < this.getLessonsGroups().size(); i++) {
                    System.out.println((i + 1) + "-" + this.getLessonsGroups().get(i).toString());
                }
                int chooseRemoveProf = Integer.parseInt(input.nextLine());
                chooseRemoveProf--;
                this.removeLesson(chooseRemoveProf);
                System.out.println("do you wanna delete more?(Y/N)");
                resume8 = input.nextLine();
            } else {
                System.out.println("there is no lesson's group to remove");
                resume8 = "Y";
                break;
            }
        } while (resume8.equals("Y"));
    }

    public void enterLessonGroupGrades(AttendanceLessonGroup gradingLessonsGroup, Scanner input) {
        if (gradingLessonsGroup.getStudnetGrades() != null && gradingLessonsGroup.getStudnetGrades().size() != 0) {
            boolean finishedGrading = false;
            do {
                System.out.println("choose the student you want to enter his/her grade in " +
                        gradingLessonsGroup.getSimpleName());
                for (int i = 0; i < gradingLessonsGroup.getStudents().size(); i++) {
                    System.out.println((i + 1) + "-" + gradingLessonsGroup.getStudents().get(i).toString());
                }
                int chooseGradingStud = Integer.parseInt(input.nextLine());
                chooseGradingStud--;

                System.out.println("enter his/her final grade in your lesson group");
                int grade = Integer.parseInt(input.nextLine());
                gradingLessonsGroup.getStudnetGrades().put(gradingLessonsGroup.getStudents().get(chooseGradingStud), grade);

                System.out.println("would you like to add more gradexto other students of this lesson group?(Y/N)");
                String keepGrading = input.nextLine();
                if (keepGrading.equals("N")) {
                    finishedGrading = true;
                    break;
                } else if (!keepGrading.equals("Y")) {
                    System.out.println("invalid input");
                    finishedGrading = true;
                    break;
                }
            } while (!finishedGrading);
        } else {
            System.out.println("there is no studnet in this lesson group");
        }
    }


    public void enterLessonGroupGrades(OnlineLessonGroup gradingLessonsGroup, Scanner input) {
        if (gradingLessonsGroup.getStudnetGrades() != null && gradingLessonsGroup.getStudnetGrades().size() != 0) {
            boolean finishedGrading = false;
            do {
                System.out.println("choose the student you want to enter his/her grade in " +
                        gradingLessonsGroup.getSimpleName());
                for (int i = 0; i < gradingLessonsGroup.getStudents().size(); i++) {
                    System.out.println((i + 1) + "-" + gradingLessonsGroup.getStudents().get(i).toString());
                }
                int chooseGradingStud = Integer.parseInt(input.nextLine());
                chooseGradingStud--;

                System.out.println("enter his/her final grade in your lesson group");
                int grade = Integer.parseInt(input.nextLine());
                gradingLessonsGroup.getStudnetGrades().put(gradingLessonsGroup.getStudents().get(chooseGradingStud), grade);

                System.out.println("would you like to add more gradexto other students of this lesson group?(Y/N)");
                String keepGrading = input.nextLine();
                if (keepGrading.equals("N")) {
                    finishedGrading = true;
                    break;
                } else if (!keepGrading.equals("Y")) {
                    System.out.println("invalid input");
                    finishedGrading = true;
                    break;
                }
            } while (!finishedGrading);
        } else {
            System.out.println("there is no studnet in this lesson group");
        }
    }

    public void checkWholeStudentsGrades(LessonsGroup gradedLessonsGroup) {
        if (gradedLessonsGroup.getStudnetGrades() != null && gradedLessonsGroup.getStudnetGrades().size() != 0) {
            for (Map.Entry<Student, Integer> studentGrades : gradedLessonsGroup.getStudnetGrades().entrySet()) {
                System.out.println("student:" + studentGrades.getKey().toString() + "last update grade: "
                        + studentGrades.getValue());
            }
        } else {
            System.out.println("there is no student in this lesson group");
        }
    }
}
