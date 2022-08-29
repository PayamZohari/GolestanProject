/**
 * Represents students class
 *
 * @author Payam Zoahrri
 * in this class we model students
 * students are also a user of golestan who are just allowed to see their lesson groups and changing therir password
 */
package ir.ac.kntu;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * studnets field besides user's(parent) field are the maximum of lesson's they can take based on the lessons value
 * this is final based on documentation(20)
 * also they have studnet number which is also their username
 * there is a list of lessons they passed with each passed one's grade and also their lessons of this term
 * there is a list of all students
 */
public class Student extends User {
    //static field
    private static ArrayList<Student> students = new ArrayList<>();
    //non-static fields
    private ArrayList<MealOrder> studentMealOrders = new ArrayList<>();
    private HashMap<LessonsGroup, Boolean> requestsResults = new HashMap<>();
    final private int maxLessonsGroup = 20;
    private int termValue = 0;
    private String studentNumber = "xxxxxxxxxx";
    private HashMap<Lesson, Integer> passedLessons = new HashMap<>();
    private ArrayList<LessonsGroup> lessonsGroup = new ArrayList<>();
    private Fields field;

    //constructor
    public Student(String firstName, String lastName, String fieldName, int fieldCode, String studentNumber,
                   String studentPassword, HashMap<Lesson, Integer> passedLessons,
                   ArrayList<LessonsGroup> lessonsGroups) {
        super(firstName, lastName, "xxxxxxxxxx", studentPassword);

        String studnetNumberPattern = "([9][7]|[9][8]|[9][9]|[0][0])[0-9]{8}";
        Pattern studentNumberPt = Pattern.compile(studnetNumberPattern);
        Matcher studnetNumberMt = studentNumberPt.matcher(studentNumber);
        boolean studentNumberCheck = studnetNumberMt.matches();
        if (studentNumberCheck == true) {
            this.studentNumber = studentNumber;
            this.setUserName(studentNumber);
        }
        if (passedLessons != null) {
            this.passedLessons = new HashMap<Lesson, Integer>(passedLessons);
        } else {
            this.passedLessons = new HashMap<>();
        }
        if (lessonsGroups != null) {
            this.lessonsGroup = new ArrayList<>(lessonsGroups);
        } else {
            this.lessonsGroup = new ArrayList<>();
        }
        this.termValue = 0;
        for (int i = 0; i < this.lessonsGroup.size(); i++) {
            this.termValue += lessonsGroup.get(i).getLessonValue();
        }

        Fields f = new Fields(fieldName, fieldCode);
        this.field = f;
        students.add(this);
        User.getUsers().add(this);
    }

    public Student(String firstName, String lastName, int entryYear, String field, String fourDigitStudnetNum,
                   String studentPassword, HashMap<Lesson, Integer> passedLessons,
                   ArrayList<LessonsGroup> lessonsGroups) {
        super(firstName, lastName, "xxxxxxxxxx", studentPassword);
        String entryYearPattern = "([9][7]|[9][8]|[9][9]|[0][0])";
        String fourDigitStudentNumPattern = "[0-9]{4}";
        Pattern entryYearPt = Pattern.compile(entryYearPattern);
        Pattern fourDigitStudentNumPt = Pattern.compile(fourDigitStudentNumPattern);
        Matcher entryYearMt = entryYearPt.matcher(String.valueOf(entryYear));
        Matcher fourDigitStudnetNumMt = fourDigitStudentNumPt.matcher(fourDigitStudnetNum);
        boolean entryYearCheck = entryYearMt.matches();

        boolean isLesson = false;
        int location = 0;
        for (int i = 0; i < Fields.getUniversityFields().size(); i++) {
            if (field.equals(Fields.getUniversityFields().get(i).getFieldName())) {
                isLesson = true;
                location = i;
                break;
            }
        }
        boolean fourDigitStudentNumCheck = fourDigitStudnetNumMt.matches();
        if (entryYearCheck == true && isLesson == true && fourDigitStudentNumCheck == true) {
            this.studentNumber += String.valueOf(entryYear) + String.valueOf((Fields.getUniversityFields().
                    get(location).getFieldCode())) + fourDigitStudnetNum;
            this.setPassword(this.studentNumber);
        }
        if (passedLessons != null) {
            this.passedLessons = new HashMap<Lesson, Integer>(passedLessons);
        }
        if (lessonsGroup != null) {
            this.lessonsGroup = new ArrayList<LessonsGroup>(lessonsGroups);
        }

        this.termValue = 0;
        for (int i = 0; i < this.lessonsGroup.size(); i++) {
            this.termValue += lessonsGroup.get(i).getLessonValue();
        }
        students.add(this);
        User.getUsers().add(this);
    }

    //getters

    public String getStudentNumber() {
        return studentNumber;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }


    public int getTermValue() {
        return termValue;
    }

    public Fields getField() {
        return field;
    }

    public int getMaxLessonsGroup() {
        return maxLessonsGroup;
    }

    public HashMap<Lesson, Integer> getPassedLessons() {
        return passedLessons;
    }

    public ArrayList<LessonsGroup> getLessonsGroup() {
        return lessonsGroup;
    }

    public HashMap<LessonsGroup, Boolean> getRequestsResults() {
        return requestsResults;
    }

    public ArrayList<MealOrder> getStudentMealOrders() {
        return studentMealOrders;
    }

    //setters
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public static void setStudents(ArrayList<Student> students) {
        Student.students = students;
    }

    public void setTermValue(int termValue) {
        this.termValue = termValue;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    public void setPassedLessons(HashMap<Lesson, Integer> passedLessons) {
        this.passedLessons = passedLessons;
    }

    public void setLessonsGroup(ArrayList<LessonsGroup> lessonsGroup) {
        this.lessonsGroup = lessonsGroup;
    }

    public void setStudentMealOrders(ArrayList<MealOrder> studentMealOrders) {
        studentMealOrders = studentMealOrders;
    }

    public void setRequestsResults(HashMap<LessonsGroup, Boolean> requestsResults) {
        this.requestsResults = requestsResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return maxLessonsGroup == student.maxLessonsGroup && termValue == student.termValue &&
                Objects.equals(studentNumber, student.studentNumber) &&
                Objects.equals(passedLessons, student.passedLessons) &&
                Objects.equals(lessonsGroup, student.lessonsGroup) && Objects.equals(field, student.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxLessonsGroup, termValue, studentNumber, passedLessons, lessonsGroup, field);
    }

    //Sign-In Method

    /**
     * it is a log in method for studnets after passing their username password
     * it contains menu of what can studnet do in golestan
     * they can see their classes in this term which has token by admin
     * also they can change their password and log out
     */
    public void logIn(Scanner input) {
        boolean stayLogIn = true;
        while (stayLogIn == true) {
            System.out.println("choose from the options below and then enter it's number");
            System.out.println("0-show current term's classes (weekly : NEW FEATURE!!)");
            System.out.println("1-change password");
            System.out.println("2-take(add) lesson groups for this term");
            System.out.println("3-passed lessons and grades (last terms)");
            System.out.println("4-send request to remove a lesson group in this term or" +
                    " check the result of last requests");
            System.out.println("5-Golestan food reservation system(NEW FEATURE!!)");
            System.out.println("9-log out");
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 0:
                    ArrayList<LessonsGroup> mondayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> tuesdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> wednesdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> thursdayLessons = new ArrayList<>();
                    ArrayList<LessonsGroup> fridayLessons = new ArrayList<>();

                    for (int i = 0; i < this.lessonsGroup.size(); i++) {
                        for (int j = 0; j < this.lessonsGroup.get(i).getGroupLessonsTime().size(); j++) {
                            if (this.lessonsGroup.get(i).getGroupLessonsTime().
                                    get(j).getWeekDays().equals(WeekDays.MONDAY)) {
                                mondayLessons.add(this.lessonsGroup.get(i));
                            } else if (this.lessonsGroup.get(i).getGroupLessonsTime().
                                    get(j).getWeekDays().equals(WeekDays.TUESDAY)) {
                                tuesdayLessons.add(this.lessonsGroup.get(i));
                            } else if (this.lessonsGroup.get(i).getGroupLessonsTime().
                                    get(j).getWeekDays().equals(WeekDays.WEDNESDAY)) {
                                wednesdayLessons.add(this.lessonsGroup.get(i));
                            } else if (this.lessonsGroup.get(i).getGroupLessonsTime().
                                    get(j).getWeekDays().equals(WeekDays.THURSDAY)) {
                                thursdayLessons.add(this.lessonsGroup.get(i));
                            } else if (this.lessonsGroup.get(i).getGroupLessonsTime().get(j).
                                    getWeekDays().equals(WeekDays.FRIDAY)) {
                                fridayLessons.add(this.lessonsGroup.get(i));
                            } else {
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
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 2:
                    this.addLessonToStudent(input);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    Double totalAvgGrades = 0.0;
                    if (passedLessons.size() != 0 && passedLessons != null) {
                        int totalLessonsValue = 0;
                        for (Map.Entry<Lesson, Integer> lesson : passedLessons.entrySet()) {
                            totalLessonsValue += lesson.getKey().getLessonValue();
                            totalAvgGrades += lesson.getValue() * lesson.getKey().getLessonValue();
                            System.out.println(lesson.getKey().toString() + " grade :" + lesson.getValue());
                        }

                        totalAvgGrades /= totalLessonsValue;
                    }
                    System.out.println("the total average of your passed lesson's grades is: " + totalAvgGrades);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                case 4:
                    System.out.println("0-add more request to remove another lesson");
                    System.out.println("1-check for earlier request's results");
                    int chooseRequest = Integer.parseInt(input.nextLine());
                    if (chooseRequest == 0) {
                        this.askForRemovingLessonGroup(input);
                    } else if (chooseRequest == 1) {
                        if (requestsResults != null && requestsResults.size() != 0) {

                            boolean checked = false;
                            for (Map.Entry<LessonsGroup, Boolean> result : this.requestsResults.entrySet()) {
                                if (result.getValue()) {
                                    System.out.println("your request for removing" + result.getKey().getSimpleName()
                                            + "has accepted");
                                } else {
                                    System.out.println("your request for removing" + result.getKey().getSimpleName()
                                            + "has denied");
                                }
                                result.setValue(null);


                                System.out.println("do you want to check more results?(Y/N)");
                                String keepAnswering = input.nextLine();
                                if (keepAnswering.equals("N")) {
                                    break;
                                } else if (!keepAnswering.equals("Y")) {
                                    System.out.println("invalid input");
                                }
                            }
                            for (Map.Entry<LessonsGroup, Boolean> result2 : this.requestsResults.entrySet()) {
                                if (result2.getValue() == null) {
                                    requestsResults.remove(result2.getKey());
                                }
                            }
                        } else {
                            System.out.println("there is no result for your requests");
                        }
                    } else {
                        System.out.println("invalid inout");
                    }
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 5:
                    System.out.println("Wellcome to food reservation system of Golestan");
                    System.out.println("choose what do you want to be done");
                    System.out.println("0-add food reservation for this week");
                    System.out.println("1-checking your earlier resrvation in this week");
                    int selectFoodReservation = Integer.parseInt(input.nextLine());
                    if (selectFoodReservation == 0) {
                        System.out.println("in which day of this week you are gonna have reservation");
                        WeekDays mealOrderWeekDay = WeekDays.valueOf(input.nextLine());
                        System.out.println("where do you want to recieve it?");
                        System.out.println("0-dining room");
                        System.out.println("1-dormitory");
                        Location studentRecieveLoc = Location.DININGROOM;
                        int chooseLocation = Integer.parseInt(input.nextLine());
                        if (chooseLocation == 1) {
                            studentRecieveLoc = Location.DORMITORY;
                        } else if (chooseLocation != 1 && chooseLocation != 0) {
                            System.out.println("invalid input");
                        }
                        System.out.println("0-breakfast reservation");
                        System.out.println("1-lunch reservation");
                        int chooseMeal = Integer.parseInt(input.nextLine());
                        if (chooseMeal == 0) {
                            System.out.println("when do you want recieve your breakfast?");
                            System.out.println("0-7:30AM");
                            System.out.println("1-9:00AM");
                            System.out.println("2-10:30AM");
                            int chooseTimeB = Integer.parseInt(input.nextLine());
                            Time studentRecieveTimeB = new Time(mealOrderWeekDay, 7, 30);
                            if (chooseTimeB == 1) {
                                studentRecieveTimeB.setHour(9);
                                studentRecieveTimeB.setMinute(0);
                            } else if (chooseTimeB == 2) {
                                studentRecieveTimeB.setHour(10);
                                studentRecieveTimeB.setMinute(30);
                            } else if (chooseTimeB > 2 || chooseTimeB < 0) {
                                System.out.println("invalid input");
                            }
                            BreakFast studentBreakFast = new BreakFast(mealOrderWeekDay);
                            MealOrder studentMealOrder = new MealOrder(this, studentBreakFast, studentRecieveTimeB,
                                    studentRecieveLoc);
                        } else if (chooseMeal == 1) {
                            System.out.println("when do you want recieve your Lunch?");
                            System.out.println("0-12:30AM");
                            System.out.println("1-14:00AM");
                            System.out.println("2-15:30AM");
                            int chooseTimeL = Integer.parseInt(input.nextLine());
                            Time studentRecieveTimeL = new Time(mealOrderWeekDay, 12, 30);
                            if (chooseTimeL == 1) {
                                studentRecieveTimeL.setHour(14);
                                studentRecieveTimeL.setMinute(0);
                            } else if (chooseTimeL == 2) {
                                studentRecieveTimeL.setHour(15);
                                studentRecieveTimeL.setMinute(30);
                            } else if (chooseTimeL > 2 || chooseTimeL < 0) {
                                System.out.println("invalid input");
                            }
                            Lunch studentLunch = new Lunch(mealOrderWeekDay, input);
                            MealOrder studentMealOrder = new MealOrder(this, studentLunch, studentRecieveTimeL
                                    , studentRecieveLoc);
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (selectFoodReservation == 1) {
                        System.out.println("these are your earlier reservation sorted based on the time you've" +
                                "reserved it");
                        if (studentMealOrders != null && studentMealOrders.size() != 0) {
                            for (int i = 0; i < this.studentMealOrders.size(); i++) {
                                System.out.println(this.studentMealOrders.get(i).toString());
                            }
                        } else {
                            System.out.println("you haven't had any reservation in this week");
                        }
                    } else {
                        System.out.println("invalid input");
                    }
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 6:

                    ////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 9:
                    System.out.println("GoodLuck " + this.getClass() + " " + this.getFirstName() +
                            " " + this.getLastName());
                    stayLogIn = false;
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                default:
                    System.out.println("invalid input");

            }
        }


    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }

    /**
     * this method simply removes a lesson for one studnets by admins call
     *
     * @param x x is the index of lessons group from this students list of class which is gonna be removed
     *          this method substracts the value of removed lesson from studennts term value
     */
    public void removeLessonGroup(int x) {
        System.out.println(x);
        System.out.println(this.lessonsGroup.size());
        if (this.lessonsGroup.size() > 0 && x < this.lessonsGroup.size() && x >= 0) {
            int check = this.getLessonsGroup().size();
            this.termValue -= this.lessonsGroup.get(x).getLessonValue();
            this.lessonsGroup.get(x).getStudnetGrades().remove(this);
            this.lessonsGroup.remove(x);
        } else {
            System.out.println("failed for studnet");
        }
    }

    //overloading
    public void removeLessonGroup(LessonsGroup otherLessonsGroup) {
        if (this.getLessonsGroup().size() > 0) {
            int check = this.getLessonsGroup().size();
            this.termValue -= otherLessonsGroup.getLessonValue();
            otherLessonsGroup.getStudnetGrades().remove(this);
            this.lessonsGroup.remove(otherLessonsGroup);
        } else {
            System.out.println("failed for studnet");
        }
    }

    public static void addStudnet(Scanner input) {
        System.out.println("first name:");
        String sFirstName = input.nextLine();
        System.out.println("last name:");
        String sLastName = input.nextLine();
        System.out.println("student field: ");
        Fields f = new Fields("Architecture", 118);
        System.out.println("do you wanna add new field?(Y/N)");
        String answer = input.nextLine();
        if (answer.equals("Y")) {
            System.out.println("studnet field name: ");
            String fieldName = input.nextLine();
            System.out.println("student field code: ");
            int fieldCode = Integer.parseInt(input.nextLine());
            f = new Fields(fieldName, fieldCode);
        } else if (answer.equals("N")) {
            System.out.println("choose field number from list below");
            for (int i = 0; i < Fields.getUniversityFields().size(); i++) {
                System.out.println((i + 1) + Fields.getUniversityFields().get(i).toString());
            }
            int chooseField = Integer.parseInt(input.nextLine());
            chooseField--;
            f = Fields.getUniversityFields().get(chooseField);
        } else {
            System.out.println("invalid input");
        }
        System.out.println("user name:");
        String sUserName = input.nextLine();
        System.out.println("password:");
        String sPassword = input.nextLine();
        System.out.println("add passed lessons");
        HashMap<Lesson, Integer> tempLesson = new HashMap<>();
        String resume1 = "";
        do {
            System.out.println("choose the number of lesson from list below");
            for (int i = 0; i < Lesson.getLessons().size(); i++) {
                if ((Lesson.getLessons().get(i) instanceof LessonsGroup) == false) {
                    System.out.println((i + 1) + ((Lesson) Lesson.getLessons().get(i)).toString());
                }
            }
            int chooseLesson = Integer.parseInt(input.nextLine());
            chooseLesson--;

            if (!tempLesson.containsKey(Lesson.getLessons().get(chooseLesson))) {
                System.out.println("enter it's grade");
                int grade = Integer.parseInt(input.nextLine());
                tempLesson.put(Lesson.getLessons().get(chooseLesson), grade);
            } else {
                System.out.println("you already have entered this lesson");
            }
            System.out.println("do you wanna add more ?(Y/N)");
            resume1 = input.nextLine();
        } while (resume1.equals("Y"));
        System.out.println("add students lessons group");
        int values = 0;
        ArrayList<LessonsGroup> sTempLessonsGroup = new ArrayList<>();
        String resume2 = "";
        do {
            System.out.println("choose the number of group lessons from list below");
            for (int i = 0; i < LessonsGroup.getLessonsGroups().size(); i++) {
                if (LessonsGroup.getLessonsGroups().get(i) instanceof OnlineLessonGroup ||
                        LessonsGroup.getLessonsGroups().get(i) instanceof AttendanceLessonGroup) {
                    System.out.println((i + 1) + LessonsGroup.getLessonsGroups().get(i).toString());
                }
            }
            int chooseGroupLesson = Integer.parseInt(input.nextLine());
            chooseGroupLesson--;

            Student temp = new Student(sFirstName, sLastName, f.getFieldName(), f.getFieldCode(),
                    sUserName, sPassword, tempLesson, sTempLessonsGroup);
            //////////////////////////////from here in enrolle//////////////////////////////////////////////////////////
            LessonsGroup.getLessonsGroups().get(chooseGroupLesson).enrolle(temp);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("do you wanna add more ?(Y/N)");
            resume2 = input.nextLine();
            if (resume2.equals("Y") == false) {
                break;
            }
        } while (resume2.equals("Y"));
        ///////////////////////////////////////////////////adding enrollable lessons
        if (sTempLessonsGroup == null) {
            sTempLessonsGroup = new ArrayList<>();
        }
        if (tempLesson == null) {
            tempLesson = new HashMap<>();
        }
        ////////////////////////////////////////////////////////////creating student object
        Student s = new Student(sFirstName, sLastName, f.getFieldName(), f.getFieldCode(),
                sUserName, sPassword, tempLesson, sTempLessonsGroup);
        System.out.println("student has just added");
        if (sTempLessonsGroup != null) {
            for (int i = 0; i < sTempLessonsGroup.size(); i++) {
                sTempLessonsGroup.get(i).getStudents().add(s);
                sTempLessonsGroup.get(i).getStudnetGrades().put(s, 0);
            }
        }
    }

    public static void editStudent(Scanner input) {
        System.out.println("choose student from list below: ");
        for (int i = 0; i < Student.getStudents().size(); i++) {
            System.out.println((i + 1) + Student.getStudents().get(i).toString());
        }
        int chooseStudent = Integer.parseInt(input.nextLine());
        chooseStudent--;
        System.out.println("choose between options below");
        System.out.println("0-add lesson's group for this student");
        System.out.println("1-remove lesson's group from this student's list: ");
        int chooseEditStud = Integer.parseInt(input.nextLine());
        if (chooseEditStud == 0) {
            Student.getStudents().get(chooseStudent).addLessonToStudent(input);
        } else if (chooseEditStud == 1) {
            Student.getStudents().get(chooseStudent).removeLessonForStudent(input);
        } else {
            System.out.println("invalid input");
        }
    }

    public void addLessonToStudent(Scanner input) {
        int values1 = this.getTermValue();
        ArrayList<LessonsGroup> esTempLessonsGroup = new ArrayList<>();
        esTempLessonsGroup = this.getLessonsGroup();
        String resume5 = "";
        do {
            System.out.println("choose the number of group lesson from list below");
            for (int i = 0; i < LessonsGroup.getLessonsGroups().size(); i++) {
                if (LessonsGroup.getLessonsGroups().get(i) instanceof OnlineLessonGroup ||
                        LessonsGroup.getLessonsGroups().get(i) instanceof AttendanceLessonGroup) {
                    System.out.println((i + 1) + LessonsGroup.getLessonsGroups().get(i).toString());
                }
            }
            int chooseGroupLesson1 = Integer.parseInt(input.nextLine());
            chooseGroupLesson1--;
            /*
            boolean isUnique1 = true;
            for (int i = 0; i < esTempLessonsGroup.size(); i++) {
                if (LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).
                        equals(esTempLessonsGroup.get(i)) == true) {
                    isUnique1 = false;
                    break;
                }
            }
            if (LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).
                    checkPreRequisiteLessons(this.getPassedLessons()) == true
                    && values1 < 20 &&
                    LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).
                            checkIntercurrentLessonGroup(this.getLessonsGroup()) == false &&
                    LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).getCapacity() > 0 &&
                    isUnique1 == true) {

                this.lessonsGroup.add(LessonsGroup.getLessonsGroups().get(chooseGroupLesson1));
                System.out.println(LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).getSimpleName()+
                        "has just added successfuly");
                LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).
                        setCapacity(LessonsGroup.getLessonsGroups().
                                get(chooseGroupLesson1).getCapacity() - 1);
                this.setTermValue(this.getTermValue() + LessonsGroup.getLessonsGroups().
                        get(chooseGroupLesson1).getLessonValue());
                LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).getStudents().
                        add(this);
                LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).getStudnetGrades().
                        put(this, 0);
            }
             */
            LessonsGroup.getLessonsGroups().get(chooseGroupLesson1).enrolle(this);
            System.out.println("do you wanna add more?(Y/N)");
            resume5 = input.nextLine();
        } while (resume5.equals("Y"));
    }

    public void removeLessonForStudent(Scanner input) {
        String resume6 = "";
        do {
            if (this.getLessonsGroup().size() != 0) {
                System.out.println("choose the lesson's group which you wanna" +
                        " remove from students lesson's groups");
                for (int i = 0; i < this.getLessonsGroup().size(); i++) {
                    System.out.println((i + 1) + "-" + this.getLessonsGroup().get(i).toString());
                }
                int chooseRemoveLesson = Integer.parseInt(input.nextLine());
                chooseRemoveLesson--;
                this.removeLessonGroup(chooseRemoveLesson);
                System.out.println("do you wanna delete more?(Y/N)");
                resume6 = input.nextLine();
            } else {
                System.out.println("there is no lesson's group left to remove");
                resume6 = "Y";
                break;
            }
        } while (resume6.equals("Y"));
    }

    public void askForRemovingLessonGroup(Scanner input) {
        if (lessonsGroup.size() != 0 && lessonsGroup != null) {
            System.out.println("choose a token lesson group from your list in this term (list below)");
            for (int i = 0; i < lessonsGroup.size(); i++) {
                System.out.println((i + 1) + "-" + lessonsGroup.get(i).toString());
            }
            int chooseRequestRemoveLesson = Integer.parseInt(input.nextLine());
            chooseRequestRemoveLesson--;

            if (Admin.getStudentsRequests().containsKey(this) && Admin.getStudentsRequests().get(this).
                    equals(lessonsGroup.get(chooseRequestRemoveLesson))) {
                System.out.println("you have already sent this request for this exact lesson");
            } else {
                Admin.getStudentsRequests().put(this, this.lessonsGroup.get(chooseRequestRemoveLesson));
            }
        } else {
            System.out.println("you have not token any lessons this term yet so you can not request " +
                    "for removing lesson");
        }

    }
}