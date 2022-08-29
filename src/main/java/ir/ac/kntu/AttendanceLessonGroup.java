/**Present attendance clesses
 * this class is a child class to LessonGroup class
 * @author Payam Zohari
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Random;

/**
 * this class has a refrence field to a ClassRoom object (composition) which is the physical place where
 * attendance classes can be held
 */
public class AttendanceLessonGroup extends LessonsGroup {
    private ClassRoom lessonGroupClassRoom;

    //constructor
    public AttendanceLessonGroup(int lessonsGroupCode, String lessonName, ArrayList<Student> students,
                                 ArrayList<Time> groupLessonsTime,
                                 int capacity, int termNumber, Professor lessonGroupProfessor,
                                 ClassRoom lessonGroupClassRoom) {
        super(lessonsGroupCode, lessonName, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);
        LessonsGroup temp = new LessonsGroup(lessonsGroupCode, lessonName, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);
        if (temp.checkIntercurrentLessonGroup(lessonGroupClassRoom.getReservedTimes()) == false) {
            if (lessonGroupClassRoom != null) {
                this.lessonGroupClassRoom = lessonGroupClassRoom;
            }
        } else {
            System.out.println("this class has been reserved for another lesson group");
            System.out.println("another class is going to be reserved for this lesson group");
            Random random = new Random();
            ClassRoom alternativeClassRoom = new ClassRoom("class" + random.nextInt(1000), 45);
            this.lessonGroupClassRoom = alternativeClassRoom;
        }
        if (capacity > this.lessonGroupClassRoom.getClassRoomCapacity()) {
            System.out.println("sorry , the physical capacity of this class room is less than " +
                    "you want, so the capacity would be the maximum capacity of class room");
            this.setCapacity(lessonGroupClassRoom.getClassRoomCapacity());
        } else if (capacity < this.lessonGroupClassRoom.getClassRoomCapacity()) {
            System.out.println("since the capacity you want to be provided is less than " +
                    "this class room capacity ,so the capacity would be exactly what you need");
            this.setCapacity(capacity);
        }

        this.getLessonGroupClassRoom().getReservedTimes().add((LessonsGroup) this);
        LessonsGroup.getLessonsGroups().add(this);
    }

    public AttendanceLessonGroup(int lessonsGroupCode, int lessonCode, ArrayList<Student> students,
                                 ArrayList<Time> groupLessonsTime,
                                 int capacity, int termNumber, Professor lessonGroupProfessor,
                                 ClassRoom lessonGroupClassRoom) {
        super(lessonsGroupCode, lessonCode, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);
        LessonsGroup temp = new LessonsGroup(lessonsGroupCode, lessonCode, students,
                groupLessonsTime,
                capacity, termNumber, lessonGroupProfessor);
        if (temp.checkIntercurrentLessonGroup(lessonGroupClassRoom.getReservedTimes()) == false) {
            if (lessonGroupClassRoom != null) {
                this.lessonGroupClassRoom = lessonGroupClassRoom;
            }
        } else {
            System.out.println("this class has been reserved for another lesson group");
            System.out.println("another class is going to be reserved for this lesson group");
            Random random = new Random();
            ClassRoom alternativeClassRoom = new ClassRoom("class" + random.nextInt(1000), 45);
            this.lessonGroupClassRoom = alternativeClassRoom;
        }
        if (capacity > this.lessonGroupClassRoom.getClassRoomCapacity()) {
            System.out.println("sorry , the physical capacity of this class room is less than " +
                    "you want, so the capacity would be the maximum capacity of class room");
            this.setCapacity(lessonGroupClassRoom.getClassRoomCapacity());
        } else {
            System.out.println("since the capacity you want to be provided is less than " +
                    "this class room capacity ,so the capacity would be exactly what you need");
            this.setCapacity(capacity);
        }

        this.getLessonGroupClassRoom().getReservedTimes().add((LessonsGroup) this);
        LessonsGroup.getLessonsGroups().add(this);
    }


    //getters

    public ClassRoom getLessonGroupClassRoom() {
        return lessonGroupClassRoom;
    }

    //setter

    public void setLessonGroupClassRoom(ClassRoom lessonGroupClassRoom) {
        this.lessonGroupClassRoom = lessonGroupClassRoom;
    }

    @Override
    public String toString() {
        return "AttendanceLessonGroup{" +
                "lessonName='" + getLessonName() + '\'' +
                ", lessonsGroupCode=" + getLessonsGroupCode() +
                ", groupLessonsTime=" + getGroupLessonsTime() +
                ", groupLessonsClassRoom=" + getLessonGroupClassRoom().toString() +
                ", lessonGroupProfessor=" + getLessonGroupProfessor() +
                ", termNumber=" + getTermNumber() +
                '}';
    }

}
