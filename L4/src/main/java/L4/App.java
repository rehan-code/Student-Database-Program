/*
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 * Lab4 of CIS2430
 */
package L4;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        int repeat = 1;
        int menuInput, i;
        String junk;
        ArrayList<Student> studentList = new ArrayList<Student>();
        Hashtable<String, Integer> index = new Hashtable<String, Integer>();
        int noStudents = 0;
        String tempInput = "";
        String programName;
        double grade, average;
        boolean isEmpty = true;
        String name = "";

        while (repeat == 1) {
            printMenu();
            try {
                menuInput = scannerObj.nextInt();
            } catch (Exception e) {
                menuInput = -1;
            }
            junk = scannerObj.nextLine();

            switch (menuInput) {
                case 1:
                    Student tempStudent = null;
                    
                    String words[] = {};
                    while (isEmpty == true) {
                        System.out.println("Enter Student last name");
                        name = scannerObj.nextLine();
                        name = name.replace('\n', '\0');
                        isEmpty = name.isEmpty();

                        if (isEmpty == false){//if they enterred the last name
                            System.out.println("Enter Student Program and Year");
                            tempInput = scannerObj.nextLine();
                            tempInput = tempInput.replace('\n', '\0');
                            isEmpty = tempInput.isEmpty();

                            if (isEmpty == false){//if they enterred the pragram and year
                                try {
                                    words = tempInput.split("[ ]+");
                                    tempStudent = new Student(name, words[0], words[1]);
                                    isEmpty = !addHash(index, name, words[0], words[1], studentList.size());//loop will stop when valid hash
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                    isEmpty = true;
                    
                    System.out.println("Enter average grade, or leave Blank");
                    tempInput = scannerObj.nextLine();
                    tempInput = tempInput.replace('\n', '\0');
                    isEmpty = tempInput.isEmpty();
                    if (isEmpty == false) {
                        grade = 0.0;
                        try {
                            grade = Double.parseDouble(tempInput);
                            tempStudent.setGrade(grade);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.exit(0);
                        }
                    }
                    isEmpty = true;
                    
                    studentList.add(tempStudent);
                    noStudents++;
                    break;

                case 2:
                    GraduateStudent tempGradStudent = null;

                    String line[] = {};
                    while (isEmpty == true) {
                        System.out.println("Enter Student last name");
                        name = scannerObj.nextLine();
                        name = name.replace('\n', '\0');
                        isEmpty = name.isEmpty();

                        if (isEmpty == false){//if they enterred the last name
                            System.out.println("Enter Student Program and Year");
                            tempInput = scannerObj.nextLine();
                            tempInput = tempInput.replace('\n', '\0');
                            isEmpty = tempInput.isEmpty();

                            if (isEmpty == false){//if they enterred the pragram and year
                                try {
                                    line = tempInput.split("[ ]+");
                                    tempGradStudent = new GraduateStudent(name, line[0], line[1]);
                                    isEmpty = !addHash(index, name, line[0], line[1], studentList.size());//loop will stop when valid hash
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                    isEmpty = true;
                    
                    
                    index.put((line[0]+line[1]+name).toLowerCase(), studentList.size());

                    System.out.println("Enter average grade, or leave Blank");
                    tempInput = scannerObj.nextLine();
                    tempInput = tempInput.replace('\n', '\0');
                    isEmpty = tempInput.isEmpty();
                    if (isEmpty == false) {
                        grade = 0.0;
                        try {
                            grade = Double.parseDouble(tempInput);
                            tempGradStudent.setGrade(grade);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.exit(0);
                        }
                    }
                    isEmpty = true;
                    
                    String supervisor = "";
                    while (isEmpty == true) {
                        System.out.println("Enter supervisor");
                        supervisor = scannerObj.nextLine();
                        supervisor = supervisor.replace('\n', '\0');
                        isEmpty = supervisor.isEmpty();
                    }
                    isEmpty = true;
                    tempGradStudent.setSupervisor(supervisor);

                    String isPhD = "";
                    while (isEmpty == true) {
                        System.out.println("Enter if they have a PhD ('true' or 'false')");
                        isPhD = scannerObj.nextLine();
                        isPhD = isPhD.replace('\n', '\0');
                        isEmpty = isPhD.isEmpty();
                    }
                    isEmpty = true;
                    tempGradStudent.setIsPhD(isPhD);

                    System.out.println("Enter undergraduate school, or leave Blank");
                    tempInput = scannerObj.nextLine();
                    tempInput = tempInput.replace('\n', '\0');
                    isEmpty = tempInput.isEmpty();
                    if (isEmpty == false) {
                        tempGradStudent.setUndergraduateSchool(tempInput);
                    }
                    isEmpty = true;

                    studentList.add(tempGradStudent);
                    noStudents++;
                    break;

                case 3:
                    System.out.println();
                    for (i = 0; i < noStudents; i++) {
                        System.out.print((studentList.get(i)).toString());
                        System.out.println();
                    }
                    break;

                case 4:
                    average = 0.0;
                    for (i = 0; i < noStudents; i++) {
                        average = average + (studentList.get(i)).getGrade();
                    }
                    average = average/noStudents ; 
                    System.out.println("Average of the average grades: " + average);
                    System.out.println("Total number of students: " + noStudents);
                    break;

                case 5:
                    System.out.println("Enter a specific program to to show all student information for that program");
                    programName = scannerObj.nextLine();
                    programName = programName.replace('\n', '\0');
                    for (i = 0; i < noStudents; i++) {
                        if ((studentList.get(i)).isEqual(programName)) {
                            System.out.println((studentList.get(i)).toString());
                            System.out.println();
                        }
                    }
                    break;

                case 6:
                    tempInput = "";
                    GraduateStudent temp2 = null;
                    Student temp1 = null;
                    while (isEmpty == true) {
                        System.out.println("Please enter the name of the input file:");
                        tempInput = scannerObj.nextLine();
                        tempInput = tempInput.replace('\n', '\0');
                        isEmpty = tempInput.isEmpty();
                    }
                    isEmpty = true;

                    String arg[] = {""};
                    try {
                        File fp = new File("src/main/resources/" + tempInput);
                        Scanner scanner = new Scanner(fp);
                    
                        if (!scanner.hasNextLine()) {
                            System.out.println("Error: File is empty");
                        }
                        while (scanner.hasNextLine()) {
                            arg = (scanner.nextLine()).split("[ ]+");
                            boolean valid;
                            if (arg.length == 4) {
                                try {
                                    temp1 = new Student(arg[0], arg[1], arg[2], Double.parseDouble(arg[3]));
                                    valid = addHash(index, arg[0], arg[1], arg[2], studentList.size());
                                    if (valid) {
                                        studentList.add(temp1);
                                        noStudents++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("on line " + (noStudents+1) + "of file. " + e.getMessage());
                                }
                                
                                
                            }else if (arg.length == 7) {
                                try {
                                    temp2 = new GraduateStudent(arg[0], arg[1], arg[2], Double.parseDouble(arg[3]), arg[4], arg[5].equals("1"), arg[6]);
                                    valid = addHash(index, arg[0], arg[1], arg[2], studentList.size());
                                    
                                    if (valid) {
                                        studentList.add(temp2);
                                        noStudents++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("on line " + (noStudents+1) + "of file. " + e.getMessage());
                                }
                            }
                                    
                        }
                    } catch (Exception e) {
                        System.out.println("Could not open file " + tempInput);
                    }
                    
                    break;

                case 7:
                    tempInput = "";
                    while (isEmpty == true) {
                        System.out.println("Please enter the name of the output file:");
                        tempInput = scannerObj.nextLine();
                        tempInput = tempInput.replace('\n', '\0');
                        isEmpty = tempInput.isEmpty();
                    }
                    isEmpty = true;
                    try {
                        PrintWriter fileWriter = new PrintWriter("src/main/resources/" + tempInput, "UTF-8");
                        for (int j = 0; j < studentList.size(); j++) {
                            fileWriter.println(  (studentList.get(j)).toStringInLine() );
                        }
                        fileWriter.close();
                    } catch (Exception e) {
                        System.out.println("failed to write");
                    }
                    break;

                case 8:
                    String studentLastName = "";
                    while (isEmpty == true) {//to get the student last name to search with
                        System.out.println("Please enter the student's last name to search with");
                        studentLastName = scannerObj.nextLine();
                        studentLastName = studentLastName.replace('\n', '\0');
                        isEmpty = studentLastName.isEmpty();
                    }
                    isEmpty = true;

                    String studentprogram = "";
                    while (isEmpty == true) {//to get the students program to search with 
                        System.out.println("Please enter the student's program to search with");
                        studentprogram = scannerObj.nextLine();
                        studentprogram = studentprogram.replace('\n', '\0');
                        isEmpty = studentprogram.isEmpty();
                    }
                    isEmpty = true;
                    
                    String studentyear = "";
                    while (isEmpty == true) {//to get the student year to search with
                        System.out.println("Please enter the student's year to search with");
                        studentyear = scannerObj.nextLine();
                        studentyear = studentyear.replace('\n', '\0');
                        isEmpty = studentyear.isEmpty();
                    }
                    isEmpty = true;
                    
                    Integer hashIndex = getHashvalue(index, studentprogram+studentyear+studentLastName);
                    if (hashIndex == -1) {
                        System.out.println(studentprogram+studentyear+studentLastName+"enterred data does not match any entry in the database");
                    } else {
                        System.out.println(  (studentList.get(hashIndex)).toString() );
                    }
                    break;
                
                case 9:
                    repeat = 0;
                    break;

                default:
                    System.out.println("Invalid option");
                    System.out.println();
                    break;
            }
        }
        
        scannerObj.close();
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
            System.out.println("There was a duplicate please change it");
            return false;
        } else {
            index.put((program+year+name).toLowerCase(), listSize);
            return true;
        }
    }
}

