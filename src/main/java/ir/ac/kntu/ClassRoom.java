/**
 * this class represents physical place in whrere in-person classes can be held
 * @author Payam Zoahari
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Objects;

/**
 * this class contains a class code and it's physical capacity
 */
public class ClassRoom {

    private static ArrayList<ClassRoom> classRooms = new ArrayList<>();

    private ArrayList<LessonsGroup> reservedTimes = new ArrayList<>();

    private String classRooomCode;

    private int classRoomCapacity;

    //constructor
    public ClassRoom(String classRooomCode, int classRoomCapacity) {
        this.classRooomCode = classRooomCode;
        if (classRoomCapacity > 0) {
            this.classRoomCapacity = classRoomCapacity;
        } else {
            classRoomCapacity = 0;
        }
        ClassRoom.getClassRooms().add(this);
    }
    //getters

    public String getClassRooomCode() {
        return classRooomCode;
    }

    public int getClassRoomCapacity() {
        return classRoomCapacity;
    }

    public ArrayList<LessonsGroup> getReservedTimes() {
        return reservedTimes;
    }

    public static ArrayList<ClassRoom> getClassRooms() {
        return classRooms;
    }

    //setters


    public void setClassRooomCode(String classRooomCode) {
        this.classRooomCode = classRooomCode;
    }

    public void setClassRoomCapacity(int classRoomCapacity) {
        this.classRoomCapacity = classRoomCapacity;
    }

    public void setReservedTimes(ArrayList<LessonsGroup> reservedTimes) {
        reservedTimes = reservedTimes;
    }

    public static void setClassRooms(ArrayList<ClassRoom> classRooms) {
        ClassRoom.classRooms = classRooms;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classRooomCode='" + classRooomCode + '\'' +
                ", classRoomCapacity=" + classRoomCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassRoom classRoom = (ClassRoom) o;
        return classRoomCapacity == classRoom.classRoomCapacity &&
                Objects.equals(reservedTimes, classRoom.reservedTimes)
                && Objects.equals(classRooomCode, classRoom.classRooomCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservedTimes, classRooomCode, classRoomCapacity);
    }
}
