/**
 * Represents lesson's group which is a subclass (specialized) from Lesson class
 *
 * @author Payam Zohari
 * the lesson's group has derived from an excisting lesson , which means one professor has token that lesson and
 * the admin gives his/her class number , time but the parent fields like prequsite lesson are just the same az other
 * professors who has token this exact lesson
 * e.g : advanced programming is a lesson , advanced programming with professor zamanian on sundays and tuesdays 15:30
 * - 17:30 is a lesson's group
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * fields besides the lesson(parent) fields are the class code ,it's student,it's time,it's professor and the capacity
 * also the term in which this lesson has token
 */
public class LessonsGroup extends Lesson
        implements Enrollable {
    //static fields
    private static ArrayList<LessonsGroup> lessonsGroups = new ArrayList<>();
    //non-static fields
    private int lessonsGroupCode = 99;
    private ArrayList<Student> students;
    private ArrayList<Time> groupLessonsTime;
    private Professor lessonGroupProfessor;
    private int capacity = 0;
    private int termNumber = 4001;
    private HashMap<Student, Integer> studnetGrades;

    //constructor
    public LessonsGroup(int lessonsGroupCode, String lessonName, ArrayList<Student> students,
                        ArrayList<Time> groupLessonsTime,
                        int capacity, int termNumber, Professor lessonGroupProfessor) {
        super(0, "", new ArrayList<>(), new ArrayList<>(), 3);
        boolean isLesson = false;
        int lessonNum = 0;
        for (int i = 0; i < Lesson.getLessons().size(); i++) {
            if (lessonName == Lesson.getLessons().get(i).getLessonName()) {
                isLesson = true;
                lessonNum = i;
                break;
            }
        }
        if (isLesson == true) {
            this.setLessonCode(Lesson.getLessons().get(lessonNum).getLessonCode());
            String lessonGroupName = "^[a-z][a-zA-Z0-9]*$";
            Pattern lNamePt = Pattern.compile(lessonGroupName);
            Matcher lNameMt = lNamePt.matcher(Lesson.getLessons().get(lessonNum).getLessonName());
            boolean lNameCheck = lNameMt.matches();
            if (lNameCheck == true) {
                this.setLessonName(Lesson.getLessons().get(lessonNum).getLessonName());
            }
            this.setPreRequisiteLessons(new ArrayList<Lesson>(Lesson.getLessons().get(lessonNum).
                    getPreRequisiteLessons()));
            if (lessonsGroupCode >= 0) {
                this.setLessonsGroupCode(lessonsGroupCode);
            }
            this.studnetGrades = new HashMap<>();
            if (students.size() != 0) {
                this.students = new ArrayList<Student>(students);
                for (Student student : this.students) {
                    studnetGrades.put(student, 0);
                }
            } else {
                this.students = new ArrayList<>();
                this.studnetGrades = new HashMap<>();
            }
            this.groupLessonsTime = new ArrayList<Time>(groupLessonsTime);
            if (capacity >= 0) {
                this.capacity = capacity;
            }
            if (termNumber >= 0) {
                this.termNumber = termNumber;
            }

            boolean isUnique = true;
            for (int i = 0; i < this.getProfessors().size(); i++) {
                if (this.getProfessors().get(i).equals(lessonGroupProfessor) == true) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique == true) {
                this.getProfessors().add(lessonGroupProfessor);
                this.lessonGroupProfessor = lessonGroupProfessor;
            }


            lessonsGroups.add(this);
        } else {
            System.out.println("there is no lesson by this name! please first add your" +
                    " lesson to lessons class list");
        }
    }

    public LessonsGroup(int lessonsGroupCode, int lessonCode, ArrayList<Student> students,
                        ArrayList<Time> groupLessonsTime, int capacity, int termNumber,
                        Professor lessonGroupProfessor) {
        super(0, "", new ArrayList<>(), new ArrayList<>(), 0);
        boolean isLesson = false;
        int lessonNum = 0;
        for (int i = 0; i < Lesson.getLessons().size(); i++) {
            if (lessonCode == Lesson.getLessons().get(i).getLessonCode()) {
                isLesson = true;
                lessonNum = i;
                break;
            }
        }
        if (isLesson == true) {
            this.setLessonCode(Lesson.getLessons().get(lessonNum).getLessonCode());
            this.setLessonName(Lesson.getLessons().get(lessonNum).getLessonName());
            this.setPreRequisiteLessons(new ArrayList<Lesson>(Lesson.getLessons().get(lessonNum).
                    getPreRequisiteLessons()));
            this.lessonGroupProfessor = lessonGroupProfessor;
            if (lessonsGroupCode >= 0) {
                this.lessonsGroupCode = lessonsGroupCode;
            }

            this.students = students;

            this.groupLessonsTime = new ArrayList<Time>(groupLessonsTime);
            if (capacity >= 0) {
                this.capacity = capacity;
            }
            if (termNumber >= 0) {
                this.termNumber = termNumber;
            } else {
                System.out.println("there is no lesson by this code! please first" +
                        " add your lesson to lessons class list");
            }
        }
    }
    //getters

    public static ArrayList<LessonsGroup> getLessonsGroups() {
        return lessonsGroups;
    }

    public int getLessonsGroupCode() {
        return lessonsGroupCode;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Time> getGroupLessonsTime() {
        return groupLessonsTime;
    }

    public Professor getLessonGroupProfessor() {
        return lessonGroupProfessor;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public HashMap<Student, Integer> getStudnetGrades() {
        return studnetGrades;
    }

    //setters

    public static void setLessonsGroups(ArrayList<LessonsGroup> lessonsGroups) {
        LessonsGroup.lessonsGroups = lessonsGroups;
    }

    public void setLessonsGroupCode(int lessonsGroupCode) {
        this.lessonsGroupCode = lessonsGroupCode;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setGroupLessonsTime(ArrayList<Time> groupLessonsTime) {
        this.groupLessonsTime = groupLessonsTime;
    }

    public void setLessonGroupProfessor(Professor lessonGroupProfessor) {
        this.lessonGroupProfessor = lessonGroupProfessor;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public void setStudnetGrades(HashMap<Student, Integer> studnetGrades) {
        this.studnetGrades = studnetGrades;
    }

    @Override
    public String toString() {
        return "LessonsGroup{" +
                "lessonName='" + getLessonName() + '\'' +
                ", lessonsGroupCode=" + lessonsGroupCode +
                ", groupLessonsTime=" + groupLessonsTime +
                ", lessonGroupProfessor=" + lessonGroupProfessor +
                ", termNumber=" + termNumber +
                '}';
    }

    public String getSimpleName() {
        return getLessonName();
    }

    /**
     * @param other is a single lesson group
     * @return true if this other lesson group with this one has intercurrency(tadakhol zamani) and false if they dont
     */
    public boolean check2lessonGroupIntercurrent(LessonsGroup other) {
        if (this.groupLessonsTime.size() != 0) {
            boolean output = false;
            for (int i = 0; i < this.groupLessonsTime.size(); i++) {
                boolean isIntercurrent = false;
                for (int j = 0; j < other.groupLessonsTime.size(); j++) {
                    if (this.groupLessonsTime.get(i).checkIntercurrent(other.
                            groupLessonsTime.get(j)) == true) {
                        isIntercurrent = true;
                        break;
                    }
                }
                if (isIntercurrent == true) {
                    output = true;
                    break;
                }
            }
            return output;
        }
        return false;
    }


    /**
     * @param others others are the list of already token lesson groups from student or professor
     * @return true if based on check2lessonsgroup method of this class, list of token classes has already filled
     * the time of lesson's group which is gonna be added and return false if it's okay to be added based on timing
     */
    public boolean checkIntercurrentLessonGroup(ArrayList<LessonsGroup> others) {
        if (others.size() != 0) {
            boolean intercurrent = false;
            for (int i = 0; i < others.size(); i++) {
                if (this.check2lessonGroupIntercurrent(others.get(i)) == true) {
                    intercurrent = true;
                    break;
                }
            }
            return intercurrent;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        LessonsGroup that = (LessonsGroup) o;
        return lessonsGroupCode == that.lessonsGroupCode && termNumber == that.
                termNumber && Objects.equals(lessonGroupProfessor, that.lessonGroupProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lessonsGroupCode, lessonGroupProfessor, termNumber);
    }

    public boolean checkLessonGroupUniqueness(ArrayList<LessonsGroup> sTempLessonsGroup) {
        boolean isUnique = true;
        if (sTempLessonsGroup != null && LessonsGroup.getLessonsGroups() != null) {
            for (int i = 0; i < sTempLessonsGroup.size(); i++) {
                if (this.equals(sTempLessonsGroup.get(i)) == true) {
                    isUnique = false;
                    break;
                }
            }
        }
        return isUnique;
    }


    public void enrolle(Student s) {

        /////////////////////////check uniqueness token lesson groups in last terms
        boolean isUniqueInlastTerms = this.checkLessonUniqueness(s.getPassedLessons());
        if (!isUniqueInlastTerms) {
            System.out.println("you have passed this lesson in last term");
        } else {
            /////////////////////////check uniqueness token lesson groups in this term
            boolean isUniqueInthisTerm = this.checkLessonGroupUniqueness(s.getLessonsGroup());
            if (!isUniqueInthisTerm) {
                System.out.println("you have token this lesson in this term");
            } else {
                //////////////////////check prerequisite lessons
                boolean passedPrequisiteLessons = this.checkPreRequisiteLessons(s.getPassedLessons());
                ////////////////////// check intercurrency
                boolean isIntercurrent = this.checkIntercurrentLessonGroup(s.getLessonsGroup());
                //////////////////////////////////////////////////////////////
                if (isUniqueInlastTerms == true && isUniqueInthisTerm == true && passedPrequisiteLessons == true &&
                        this.getCapacity() > 0 && (s.getTermValue() + this.getLessonValue() <= 20)
                        && isIntercurrent == false) {
                    s.getLessonsGroup().add(this);
                    this.setCapacity(this.getCapacity() - 1);
                    s.setTermValue(s.getTermValue() + this.getLessonValue());
                    this.studnetGrades.put(s, 0);
                    System.out.println("the lesson group has just added to student's list successfuly");
                } else {
                    System.out.println("adding failed");
                }
            }
        }
    }

    //overload enrolle for professors lesson group's register
    public void enrolle(ArrayList<LessonsGroup> pTempLessonsGroup) {
        //check intercurrency with other token lesson groups in this term/////////////////////////////////////////
        boolean pIntercurrent = false;
        for (int k = 0; k < this.groupLessonsTime.size(); k++) {
            if (this.checkIntercurrentLessonGroup(pTempLessonsGroup) == true) {
                pIntercurrent = true;
                break;
            }
        }
        if (pIntercurrent == false) {
            pTempLessonsGroup.add(this);
            System.out.println("one lesson group has just been added succesfully");
        } else {
            LessonsGroup.getLessonsGroups().remove(this);
            System.out.println("you cannot take this class because it" +
                    " hase intercurrent with other classes of your's");
        }
    }
}
