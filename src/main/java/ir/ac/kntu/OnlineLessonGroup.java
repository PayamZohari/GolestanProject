/**Present online clesses
 * this class is a child class to LessonGroup class
 * @author Payam Zohari
 */

package ir.ac.kntu;

import java.util.ArrayList;

/**
 * the subclass field represents how many students can be online at same time (1000)
 */
public class OnlineLessonGroup extends LessonsGroup {
    private final int maximumOnlineAttendanceCapacity = 1000;

    //constructor
    public OnlineLessonGroup(int lessonsGroupCode, String lessonName, ArrayList<Student> students,
                             ArrayList<Time> groupLessonsTime,
                             int capacity, int termNumber, Professor lessonGroupProfessor) {
        super(lessonsGroupCode, lessonName, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);

        int capacityTemp = maximumOnlineAttendanceCapacity;
        for (int i = 0; i < LessonsGroup.getLessonsGroups().size(); i++) {
            if (LessonsGroup.getLessonsGroups().get(i) instanceof OnlineLessonGroup) {
                if (this.check2lessonGroupIntercurrent(LessonsGroup.getLessonsGroups().get(i)) == true) {
                    capacityTemp -= LessonsGroup.getLessonsGroups().get(i).getCapacity();
                }
            }
        }
        if (capacityTemp >= capacity) {
            LessonsGroup.getLessonsGroups().add(this);
        } else {
            System.out.println("sorry we cannot afford this amount of students being online at same time");
        }
    }

    public OnlineLessonGroup(int lessonsGroupCode, int lessonCode, ArrayList<Student> students,
                             ArrayList<Time> groupLessonsTime,
                             int capacity, int termNumber, Professor lessonGroupProfessor) {
        super(lessonsGroupCode, lessonCode, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);

        int capacityTemp = maximumOnlineAttendanceCapacity;
        for (int i = 0; i < LessonsGroup.getLessonsGroups().size(); i++) {
            if (LessonsGroup.getLessonsGroups().get(i) instanceof OnlineLessonGroup) {
                if (this.check2lessonGroupIntercurrent(LessonsGroup.getLessonsGroups().get(i)) == true) {
                    capacityTemp -= LessonsGroup.getLessonsGroups().get(i).getCapacity();
                }
            }
        }
        if (capacityTemp >= capacity) {
            LessonsGroup.getLessonsGroups().add(this);
        } else {
            System.out.println("sorry we cannot afford this amount of students being online at same time");
        }
    }
    //getters


    public int getMaximumOnlineAttendanceCapacity() {
        return maximumOnlineAttendanceCapacity;
    }


    @Override
    public String toString() {
        return "OnlineLessonGroup{" +
                "lessonName='" + getLessonName() + '\'' +
                ", lessonsGroupCode=" + getLessonsGroupCode() +
                ", groupLessonsTime=" + getGroupLessonsTime() +
                ", lessonGroupProfessor=" + getLessonGroupProfessor() +
                ", termNumber=" + getTermNumber() +
                '}';
    }
}
