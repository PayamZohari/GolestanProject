/**
 * Represents university fields
 *
 * @author Payam Zohari
 * this is a class for fields in university (it could be better by enum!!!)
 */
package ir.ac.kntu;

import java.util.ArrayList;

/**
 * fields of a field (major) are it's name and code
 */
public class Fields {
    //static field
    private static ArrayList<Fields> universityFields = new ArrayList<>();
    //non-static fields
    private String fieldName;
    private int fieldCode;

    //constructor
    public Fields(String fieldName, int fieldCode) {
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
        universityFields.add(this);
    }
    //getters

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldCode() {
        return fieldCode;
    }

    //setters
    public static void setUniversityFields(ArrayList<Fields> universityFields) {
        Fields.universityFields = universityFields;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldCode(int fieldCode) {
        this.fieldCode = fieldCode;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldCode=" + fieldCode +
                '}';
    }

    public static ArrayList<Fields> getUniversityFields() {

        return universityFields;
    }
}
