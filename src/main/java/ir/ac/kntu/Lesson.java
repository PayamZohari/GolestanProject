/**
 * Represents lesson(study class) programming class
 *
 * @author : Payam zohari
 * this class is used for generalizing lessons
 * it doesn't contain specializing field like the professor but it contains the list of professors who might
 * take this lesson and each professor if take this lesson has it's own class code, timing and so forth
 */

package ir.ac.kntu;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * the fields are the lesson's name , lesson's code , prerequsite lesson's for this lesson , professors
 * who want to take it this term and also it's value(e.g riazi1 value is 3)
 */
public class Lesson {
    //static fields
    private static ArrayList<Lesson> lessons = new ArrayList<>();
    //non-staticfields
    private int lessonCode;
    private int lessonValue = 3;
    private String lessonName;
    private ArrayList<Lesson> preRequisiteLessons = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();

    //constructor
    Lesson(int lessonCode, String lessonName, ArrayList<Lesson> preRequisiteLessons,
           ArrayList<Professor> professors, int lessonValue) {
        this.lessonCode = lessonCode;

        String lessonNamePattern = "^[a-z][a-zA-Z0-9]*$";
        Pattern lessonNamePt = Pattern.compile(lessonNamePattern);
        Matcher lessonNameMt = lessonNamePt.matcher(lessonName);
        boolean lessonNameCheck = lessonNameMt.matches();
        if (lessonNameCheck == true) {
            this.lessonName = lessonName;
        }
        if (preRequisiteLessons != null) {
            if (preRequisiteLessons.size() != 0) {
                this.preRequisiteLessons = new ArrayList<Lesson>(preRequisiteLessons);
            } else {
                this.preRequisiteLessons = new ArrayList<>();
            }
        } else {
            this.preRequisiteLessons = new ArrayList<>();
        }

        if (professors != null) {
            this.professors = new ArrayList<Professor>(professors);
        } else {
            this.professors = new ArrayList<>();
        }
        lessons.add(this);
    }

    //getters
    public int getLessonCode() {
        return this.lessonCode;
    }

    public String getLessonName() {
        return this.lessonName;
    }

    public ArrayList<Lesson> getPreRequisiteLessons() {
        return new ArrayList<Lesson>(this.preRequisiteLessons);
    }

    public ArrayList<Professor> getProfessors() {
        return new ArrayList<Professor>(professors);
    }

    public int getLessonValue() {
        return lessonValue;
    }

    public static ArrayList<Lesson> getLessons() {
        return lessons;
    }

    //setters
    public void setLessonCode(int lessonCode) {
        this.lessonCode = lessonCode;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setLessonValue(int lessonValue) {
        this.lessonValue = lessonValue;
    }

    public void setPreRequisiteLessons(ArrayList<Lesson> preRequisiteLessons) {
        this.preRequisiteLessons = new ArrayList<Lesson>(preRequisiteLessons);
        if (lessonValue > 0) {
            this.lessonValue = lessonValue;
        }
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public static void setLessons(ArrayList<Lesson> lessons) {
        Lesson.lessons = lessons;
    }

    /**
     * @param o contains an Object object in general , which with down casting(specialization) turns into a lesson
     * @return true or false depending on whether two lesson's have same name and code or they haven't
     */
    //overrided methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lesson lesson = (Lesson) o;
        return lessonCode == lesson.lessonCode && Objects.equals(lessonName, lesson.lessonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonCode, lessonName);
    }

    //methods

    /**
     * @param passedLessons which is a hashmap of lessons which the student has passed and it's grade
     * @return true if the student has passed(grade greater than 10) every single prelesson which are necessary to pass
     * and returns fasle if he/she hasn't
     */
    public boolean checkPreRequisiteLessons(HashMap<Lesson, Integer> passedLessons) {
        boolean checkPreRequisiteLessons = true;
        if (this.preRequisiteLessons.size() != 0) {
            boolean passed = false;
            System.out.println(preRequisiteLessons.size());
            for (int i = 0; i < preRequisiteLessons.size(); i++) {
                boolean passedEach = true;

                for (HashMap.Entry<Lesson, Integer> h : passedLessons.entrySet()) {
                    if (h.getKey().equals(preRequisiteLessons.get(i)) && h.getValue() > 10) {
                        passedEach = true;
                        break;
                    }
                }
                if (passedEach == false) {
                    passed = false;
                    break;
                }
            }
            if (passed == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return checkPreRequisiteLessons;
        }
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonCode=" + lessonCode +
                ", lessonValue=" + lessonValue +
                ", lessonName='" + lessonName + '\'' +
                ", preRequisiteLessons=" + preRequisiteLessons +
                ", professors=" + professors +
                '}';
    }

    public static void addLesson(Scanner input) {
        System.out.println("lesson name: ");
        String lName = input.nextLine();
        System.out.println("lesson code: ");
        int lCode = Integer.parseInt(input.nextLine());
        System.out.println("lesson value: ");
        int lValue = Integer.parseInt(input.nextLine());
        System.out.println("prerequsite lessons: ");
        ArrayList<Lesson> lPrequsiteLessons = new ArrayList<>();
        String resume4 = "";
        do {
            System.out.println("choose lesson from list below: ");
            for (int i = 0; i < Lesson.getLessons().size(); i++) {
                if ((Lesson.getLessons().get(i) instanceof LessonsGroup) == false) {
                    System.out.println((i + 1) + Lesson.getLessons().get(i).toString());
                }
            }
            int lChooseLesson = Integer.parseInt(input.nextLine());
            lChooseLesson--;
            lPrequsiteLessons.add(Lesson.getLessons().get(lChooseLesson));
            System.out.println("do you wanna add more?(Y/N)");
            resume4 = input.nextLine();
        } while (resume4.equals("Y"));
        ArrayList<Professor> lProfessors = new ArrayList<>();
        Lesson lesson = new Lesson(lCode, lName, lPrequsiteLessons, lProfessors, lValue);
        System.out.println("Lesson has just been added successfully");
    }

    public boolean checkLessonUniqueness(HashMap<Lesson, Integer> others) {
        boolean isUnique = true;
        for (Map.Entry<Lesson, Integer> other : others.entrySet()) {
            if (this.equals(other.getKey()) && other.getValue() >= 10) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }
}
