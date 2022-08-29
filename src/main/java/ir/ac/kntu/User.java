/**
 * this is an abstract parent class for all of the users of golestan system
 * it is abstract and you cannot create an object directly (without inheritence) from it
 * it's abstract since there is no user in this system who doesnt belongs to students list, professors list and
 * admins list
 */

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * the fields are just common fields between the users of golestan(first name last name and user password)
 * also there is list of all users
 */
abstract class User {
    //static field
    private static ArrayList<User> users = new ArrayList<>();
    //non-static fields
    private String firstName = "unknown";
    private String lastName = "user";
    private String userName = "xxxxxxxxxx";
    private String password = "xxxxxxxxxxxx";


    //constructor
    public User(String firstName, String lastName, String userName, String password) {
        String namePattern = "[A-Z][a-z]{1,15}";
        Pattern namePt = Pattern.compile(namePattern);

        Matcher firstNameMt = namePt.matcher(firstName);
        boolean firstNameCheck = firstNameMt.matches();
        if (firstNameCheck == true) {
            this.firstName = firstName;
        }

        Matcher lastNameMt = namePt.matcher(lastName);
        boolean lastNameCheck = lastNameMt.matches();
        if (lastNameCheck == true) {
            this.lastName = lastName;
        }

        String userPattern = "[0-9]{10}";
        Pattern userPt = Pattern.compile(userPattern);
        Matcher userMt = userPt.matcher(userName);
        boolean userCheck = userMt.matches();
        if (userCheck == true) {
            this.userName = userName;
        }

        String passwordPattern1 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,12})";
        String passwordPattern2 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*<>.,/?]).{8,12})";
        Pattern pt41 = Pattern.compile(passwordPattern1);
        Pattern pt42 = Pattern.compile(passwordPattern2);
        Matcher mt41 = pt41.matcher(password);
        Matcher mt42 = pt42.matcher(password);
        boolean passwordCheck = (mt41.matches() || mt42.matches());
        if (passwordCheck == true) {
            this.password = password;
        }
    }
    //getters


    public static ArrayList<User> getUsers() {
        return users;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    //setters

    public void setFirstName(String firstName) {
        String namePattern = "[A-Z][a-z]{1,15}";
        Pattern namePt = Pattern.compile(namePattern);

        Matcher firstNameMt = namePt.matcher(firstName);
        boolean firstNameCheck = firstNameMt.matches();
        if (firstNameCheck == true) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        String namePattern = "[A-Z][a-z]{1,15}";
        Pattern namePt = Pattern.compile(namePattern);
        Matcher lastNameMt = namePt.matcher(lastName);
        boolean lastNameCheck = lastNameMt.matches();
        if (lastNameCheck == true) {
            this.lastName = lastName;
        }
    }

    public void setUserName(String userName) {
        String userPattern = "[0-9]{10}";
        Pattern userPt = Pattern.compile(userPattern);
        Matcher userMt = userPt.matcher(userName);
        boolean userCheck = userMt.matches();
        if (userCheck == true) {
            this.userName = userName;
        }
    }

    public void setPassword(String password) {
        String passwordPattern1 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,12})";
        String passwordPattern2 = "((?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*<>.,/?]).{8,12})";
        Pattern pt41 = Pattern.compile(passwordPattern1);
        Pattern pt42 = Pattern.compile(passwordPattern2);
        Matcher mt41 = pt41.matcher(password);
        Matcher mt42 = pt42.matcher(password);
        boolean passwordCheck = (mt41.matches() || mt42.matches());
        if (passwordCheck == true) {
            this.password = password;
        }
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    //log-in method
    public void userLogIn(String userName, String password, Scanner input) {
        boolean isCorrect = false;
        int location = 0;
        for (int i = 0; i < users.size(); i++) {
            if ((userName.equals(users.get(i).userName) &&
                    password.equals(users.get(i).password))) {
                location = i;
                isCorrect = true;
                break;
            }
        }
        if (isCorrect == true) {
            System.out.println("Welcome " + users.get(location).getClass() + " " + users.
                    get(location).getFirstName() + " " + users.get(location).getLastName());
            if ((users.get(location)) instanceof Student) {
                Student temp = (Student) users.get(location);
                temp.logIn(input);
            } else if ((users.get(location)) instanceof Professor) {
                Professor temp = (Professor) users.get(location);
                temp.logIn(input);
            } else if ((users.get(location)) instanceof Admin) {
                Admin temp = (Admin) users.get(location);
                temp.logIn(input);
            }
        } else {
            boolean isUser = false;
            for (int i = 0; i < users.size(); i++) {
                if (userName.equals(users.get(i).userName)) {
                    isUser = true;
                    break;
                }
            }
            if (isUser == true) {
                System.out.println("Wrong password");
            } else {
                System.out.println("there is no user by this username:" + userName);
            }
        }


    }
}

