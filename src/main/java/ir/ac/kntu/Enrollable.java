/**
 * this is an interface which simply is gonna implemeneted by lessongroups and it's childs
 * represents how enrolle process works
 * @author Payam Zohari
 */
package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;

public interface Enrollable {
    //for students created lesson groups
    void enrolle(Student s);

    //for professors created lesson groups
    void enrolle(ArrayList<LessonsGroup> lessonsGroups);

}
