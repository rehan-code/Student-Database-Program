/*
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 * Lab5 of CIS2430
 */
package L5;

import java.io.*;
import java.util.*;

public class App {
    static ArrayList<Student> studentList = new ArrayList<Student>();
    static Hashtable<String, Integer> index = new Hashtable<String, Integer>();

    public static void main(String[] args) {
        GUI gui = new GUI( );
        gui.setVisible(true);
    }
    
    public static void printMenu() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("(1) Enter information for a new Student");
        System.out.println("(2) Enter information for a new GraduateStudent");
        System.out.println("(3) Show all student information with each attribute on a seperate line");
        System.out.println("(4) Print the average of the average grades for class, as well as the total number of students");
        System.out.println("(5) Enter a specific program and show all student information for that program");
        System.out.println("(6) Load Student information from an input file");
        System.out.println("(7) Save all student information to an output file");
        System.out.println("(8) Lookup via HashMap with program, year, and lastName");
        System.out.println("(9) End program");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public static Integer getHashvalue(Hashtable<String, Integer> index, String searchKey) {
        Integer matchingHash;
        
        if (index.containsKey(searchKey.toLowerCase())) {
            matchingHash = index.get(searchKey.toLowerCase());
            return matchingHash;
        } else {
            return -1;
        }
    }

    public static boolean addHash(Hashtable<String, Integer> index, String name, String program, String year, int listSize) {
        if (index.containsKey((program+year+name).toLowerCase())) {
            //System.out.println("There was a duplicate please change it");
            return false;
        } else {
            index.put((program+year+name).toLowerCase(), listSize);
            return true;
        }
    }


}

