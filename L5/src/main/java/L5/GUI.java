/*
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 * Lab5 of CIS2430
 */
package L5;

import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

import java.io.*;

@SuppressWarnings("unchecked")
public class GUI extends JFrame{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 510;

    private JPanel addPanel;
    private JPanel printAvgPanel;
    private JPanel readFilePanel;
    private JPanel fileDumpPanel;
    private JPanel printInfoPanel;
    private JPanel searchPanel;

    private JTextArea memoDisplay;
    private JTextArea ifmemoDisplay;
    private JTextArea ofmemoDisplay;
    private JTextArea searchMemoDisplay;
    private JTextArea studmemoDisplay;
    private JTextField lastNameText;
    private JTextField programText;
    private JTextField yearText;
    private JTextField averageGradeText;
    private JTextField supervisorText;
    private JTextField undergraduateSchoolText;
    private JTextField readFileNameText;
    private JTextField fileDumpNameText;
    JTextField searchProgramText;
    JTextField searchYearText;
    JTextField searchNameText;
    private JPanel supervisorPanel;
    private JPanel isPhDPanel;
    private JPanel undergraduateSchoolPanel;
    private JLabel avgStringLabel;

    private double average = 0;
    private boolean isPhd = false;
    private boolean isStudent = true;
    

    private class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(true);
            printAvgPanel.setVisible(false);
            readFilePanel.setVisible(false);
            fileDumpPanel.setVisible(false);
            printInfoPanel.setVisible(false);
            searchPanel.setVisible(false);


        }
    } //End of AddListener inner class

    private class printAvgListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(false);
            printAvgPanel.setVisible(true);
            readFilePanel.setVisible(false);
            fileDumpPanel.setVisible(false);
            printInfoPanel.setVisible(false);
            searchPanel.setVisible(false);

            int i;
            for (i = 0; i < (App.studentList).size(); i++) {
                average = average + ((App.studentList).get(i)).getGrade();
            }
            if (App.studentList.size() != 0) {
                average = average/App.studentList.size() ;
            }
            avgStringLabel.setText("<html>Average of the average grades: <html>" + average + "<html><br/>Total number of students: <html>" + App.studentList.size());
        }
    } //End of printAvgListener inner class

    private class ReadFileListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(false);
            printAvgPanel.setVisible(false);
            readFilePanel.setVisible(true);
            fileDumpPanel.setVisible(false);
            printInfoPanel.setVisible(false);
            searchPanel.setVisible(false);
        }
    } //End of ReadFileListener inner class

    private class ReadFileButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean success = true;
            GraduateStudent temp2 = null;
            Student temp1 = null;

            String arg[] = {""};
            try {
                File fp = new File("src/main/resources/" + readFileNameText.getText());
                Scanner scanner = new Scanner(fp);
            
                if (!scanner.hasNextLine()) {
                    ifmemoDisplay.setText("error: file is empty");
                }
                while (scanner.hasNextLine()) {
                    arg = (scanner.nextLine()).split("[ ]+");
                    boolean valid;
                    if (arg.length == 4) {
                        try {
                            temp1 = new Student(arg[0], arg[1], arg[2], Double.parseDouble(arg[3]));
                            valid = App.addHash(App.index, arg[0], arg[1], arg[2],(App.studentList).size());
                            if (valid) {
                                (App.studentList).add(temp1);
                            }
                        } catch (Exception e1) {
                            ifmemoDisplay.setText("on line " + ((App.studentList).size()+1) + "of file. " + e1.getMessage());
                        }
                        
                    }else if (arg.length == 7) {
                        try {
                            temp2 = new GraduateStudent(arg[0], arg[1], arg[2], Double.parseDouble(arg[3]), arg[4], arg[5].equals("1"), arg[6]);
                            valid = App.addHash(App.index, arg[0], arg[1], arg[2], (App.studentList).size());
                            
                            if (valid) {
                                (App.studentList).add(temp2);
                            }
                        } catch (Exception e1) {
                            ifmemoDisplay.setText("on line " + ((App.studentList).size()+1) + "of file. " + e1.getMessage());
                        }
                    }
                            
                }
            } catch (Exception ex) {
                ifmemoDisplay.setText("error: Invalid filename");
                success = false;
            }
            if (success == true) {
                ifmemoDisplay.setText("successfully read input file");
            }
        }
    } //End of ReadFileButtonListener inner class

    private class fileDumpListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(false);
            printAvgPanel.setVisible(false);
            readFilePanel.setVisible(false);
            fileDumpPanel.setVisible(true);
            printInfoPanel.setVisible(false);
            searchPanel.setVisible(false);
        }
    } //End of fileDumpListener inner class

    private class fileDumpButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean success = true;
            try {
                PrintWriter fileWriter = new PrintWriter("src/main/resources/" + fileDumpNameText.getText(), "UTF-8");
                for (int j = 0; j < (App.studentList).size(); j++) {
                    fileWriter.println(  ((App.studentList).get(j)).toStringInLine() );
                }
                fileWriter.close();
            } catch (Exception ex) {
                ofmemoDisplay.setText("error: invalid file. Failed to write");
                success = false;
            }
            if (success == true) {
                ofmemoDisplay.setText("successfully save data to output file");
            }
        }
    } //End of fileDumpButtonListener inner class

    private class printInfoListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(false);
            printAvgPanel.setVisible(false);
            readFilePanel.setVisible(false);
            fileDumpPanel.setVisible(false);
            printInfoPanel.setVisible(true);
            searchPanel.setVisible(false);

            String allInfoString = "";
            int i;
            for (i = 0; i < App.studentList.size(); i++) {
                allInfoString = allInfoString + ((App.studentList).get(i)).toString() + "\n";
            }
            memoDisplay.setText(allInfoString);
        }
    } //End of printInfoListener inner class

    private class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            addPanel.setVisible(false);
            printAvgPanel.setVisible(false);
            readFilePanel.setVisible(false);
            fileDumpPanel.setVisible(false);
            printInfoPanel.setVisible(false);
            searchPanel.setVisible(true);
        }
    } //End of SearchListener inner class

    private class QuitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    } //End of QuitListener inner class

    private class StudentTypeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            String PName = (String)cb.getSelectedItem();
            if (PName.equals("Student")) {
                supervisorPanel.setVisible(false);
                isPhDPanel.setVisible(false);
                undergraduateSchoolPanel.setVisible(false);
                isStudent = true;
            } else if (PName.equals("Graduate Student")) {
                supervisorPanel.setVisible(true);
                isPhDPanel.setVisible(true);
                undergraduateSchoolPanel.setVisible(true);
                isStudent = false;
            }
        }
    } //End of StudentTypeListener inner class

    private class IsPhdListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            String PName = (String)cb.getSelectedItem();
            if (PName.equals("no")) {
                isPhd = false;
            } else if (PName.equals("yes")) {
                isPhd = true;
            }
        }
    } //End of IsPhdListener inner class

    private class StudentResetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            lastNameText.setText("");
            programText.setText("");
            yearText.setText("");
            averageGradeText.setText("");
            supervisorText.setText("");
            undergraduateSchoolText.setText("");
        }
    } //End of searchProductResetButtonListener inner class

    private class StudentAddButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean success = true;
            Student tempStudent = null;
            GraduateStudent tempGradStudent = null;

            studmemoDisplay.setText("");
            if (isStudent == true) {
                if (!(lastNameText.getText().isEmpty()) && !(programText.getText().isEmpty()) && !(yearText.getText().isEmpty())){//if they enterred the pragram and year
                    try {
                        tempStudent = new Student(lastNameText.getText(), programText.getText(), yearText.getText());
                    } catch (Exception ex) {
                        studmemoDisplay.setText(ex.getMessage());
                        return;
                    }
                }else {
                    studmemoDisplay.setText("Error: Last Name, Program and Year must be enterred");
                    return;
                }
                if (success ==false) {
                    studmemoDisplay.setText("Error: data enterred is a duplicate");
                    return;
                }
    
                if (!(averageGradeText.getText().isEmpty())) {
                    double grade = 0.0;
                    try {
                        grade = Double.parseDouble(averageGradeText.getText());
                        tempStudent.setGrade(grade);
                    } catch (Exception ex) {
                        studmemoDisplay.setText("Error: could not convert grade to double");
                        return;
                    }
                }
                
                try {
                    success = App.addHash(App.index, lastNameText.getText(), programText.getText(), yearText.getText(), (App.studentList).size());
                } catch (Exception ex) {
                    studmemoDisplay.setText(ex.getMessage());
                    return;
                }
                if (success ==false) {
                    studmemoDisplay.setText("Error: data enterred is a duplicate");
                    return;
                }
                (App.studentList).add(tempStudent);

            }else {
                if (!(lastNameText.getText().isEmpty()) && !(programText.getText().isEmpty()) && !(yearText.getText().isEmpty())){//if they enterred the pragram and year
                    try {
                        tempGradStudent = new GraduateStudent(lastNameText.getText(), programText.getText(), yearText.getText());
                    } catch (Exception ex) {
                        studmemoDisplay.setText(ex.getMessage());
                        return;
                    }
                }else {
                    studmemoDisplay.setText("Error: Last Name, Program and Year must be enterred");
                    return;
                }
    
                if (!(averageGradeText.getText().isEmpty())) {
                    double grade = 0.0;
                    try {
                        grade = Double.parseDouble(averageGradeText.getText());
                        tempGradStudent.setGrade(grade);
                    } catch (Exception ex) {
                        studmemoDisplay.setText("Error: could not convert grade to double");
                        return;
                    }
                }
                
                if ((supervisorText.getText().isEmpty())) {
                    studmemoDisplay.setText("Error: supervisor must be entered");
                    return;
                }
                tempGradStudent.setSupervisor(supervisorText.getText());

                if (isPhd) {
                    tempGradStudent.setIsPhD("true");
                }else {
                    tempGradStudent.setIsPhD("false");
                }
                
                if (!(undergraduateSchoolText.getText().isEmpty())) {
                    tempGradStudent.setUndergraduateSchool(undergraduateSchoolText.getText());
                }
                
                try {
                    success = App.addHash(App.index, lastNameText.getText(), programText.getText(), yearText.getText(), (App.studentList).size());
                } catch (Exception ex) {
                    studmemoDisplay.setText(ex.getMessage());
                    return;
                }
                if (success ==false) {
                    studmemoDisplay.setText("Error: data enterred is a duplicate");
                    return;
                }
                (App.studentList).add(tempGradStudent);
            }
            studmemoDisplay.setText("successfully added student");
        }
    } //End of searchProductResetButtonListener inner class

    private class SearchHKeyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Integer hashIndex = App.getHashvalue(App.index, searchProgramText.getText() + searchYearText.getText() + searchNameText.getText());
            if (hashIndex == -1) {
                searchMemoDisplay.setText(searchProgramText.getText() + searchYearText.getText() + searchNameText.getText()+"entered data does not match any entry in the database");
            } else {
                searchMemoDisplay.setText(  ((App.studentList).get(hashIndex)).toString() );
            }
        }
    } //End of SearchHKeyListener inner class

    public GUI() {
        super("GUI");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));


        //##### add student
        addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());
        //addPanel.setBackground(Color.DARK_GRAY);
        addPanel.setVisible(true);
        add(addPanel);

        //->Add student Panel Box layout for entrees
        JPanel addStudentPanel = new JPanel();
        addStudentPanel.setLayout(new BoxLayout(addStudentPanel, BoxLayout.Y_AXIS));
        addStudentPanel.setVisible(true);
        addPanel.add(addStudentPanel, BorderLayout.CENTER);

        //->-> the label entree for the Add student Panel
        
        JPanel addStudentLabelFlowPanel = new JPanel();
        addStudentLabelFlowPanel.setLayout(new FlowLayout());
        addStudentPanel.add(addStudentLabelFlowPanel);
        JLabel addStudentLabel = new JLabel("Add a student");
        //welcomeMsg.setForeground(Color.WHITE);
        addStudentLabelFlowPanel.add(addStudentLabel);

        //->-> the Type boxpanel entree for the Add student Panel
        JPanel typeStudentPanel = new JPanel( );
        typeStudentPanel.setLayout(new FlowLayout( ));
        addStudentPanel.add(typeStudentPanel);
        //->->-> the type label for the type student panel
        JLabel typeLabel = new JLabel("Type of student: ");
        typeStudentPanel.add(typeLabel);
        //->->-> the combo box for the type of student
        String[] studentType = {"Student", "Graduate Student"};
        JComboBox studentTypeComboBox = new JComboBox(studentType);
        studentTypeComboBox.setSelectedIndex(0);
        studentTypeComboBox.addActionListener(new StudentTypeListener( ));
        typeStudentPanel.add(studentTypeComboBox);

        //->-> the lastName entree for the Add student Panel
        JPanel lastNamePanel = new JPanel( );
        lastNamePanel.setLayout(new FlowLayout( ));
        addStudentPanel.add(lastNamePanel);
        //->->-> the lastName label for the lastName
        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNamePanel.add(lastNameLabel);
        //->->-> the text box for the Last Name
        lastNameText = new JTextField(10);
        //String inputString = lastNameText.getText();
        lastNamePanel.add(lastNameText);

        //->-> the program entree for the Add student Panel
        JPanel programPanel = new JPanel( );
        programPanel.setLayout(new FlowLayout( ));
        addStudentPanel.add(programPanel);
        //->->-> the program label for the program
        JLabel programLabel = new JLabel("Program: ");
        programPanel.add(programLabel);
        //->->-> the text box for the program
        programText = new JTextField(10);
        //String inputString = programText.getText();
        programPanel.add(programText);

        //->-> the year entree for the Add student Panel
        JPanel yearPanel = new JPanel( );
        yearPanel.setLayout(new FlowLayout( ));
        addStudentPanel.add(yearPanel);
        //->->-> the year label for the year
        JLabel yearLabel = new JLabel("Year: ");
        yearPanel.add(yearLabel);
        //->->-> the text box for the year
        yearText = new JTextField(4);
        //String inputString = yearText.getText();
        yearPanel.add(yearText);
        
        //->-> the averageGrade entree for the Add student Panel
        JPanel averageGradePanel = new JPanel( );
        averageGradePanel.setLayout(new FlowLayout( ));
        addStudentPanel.add(averageGradePanel);
        //->->-> the averageGrade label for the averageGrade
        JLabel averageGradeLabel = new JLabel("Average Grade: ");
        averageGradePanel.add(averageGradeLabel);
        //->->-> the text box for the averageGrade
        averageGradeText = new JTextField(4);
        //String inputString = averageGradeText.getText();
        averageGradePanel.add(averageGradeText);

        //->-> the supervisor entree for the Add student Panel
        supervisorPanel = new JPanel( );
        supervisorPanel.setLayout(new FlowLayout( ));
        supervisorPanel.setVisible(false);
        addStudentPanel.add(supervisorPanel);
        //->->-> the supervisor label for the supervisor
        JLabel supervisorLabel = new JLabel("Supervisor Name: ");
        supervisorPanel.add(supervisorLabel);
        //->->-> the text box for the author
        supervisorText = new JTextField(10);
        //String inputString = supervisorText.getText();
        supervisorPanel.add(supervisorText);

        //->-> the isPhD entree for the Add student Panel
        isPhDPanel = new JPanel( );
        isPhDPanel.setLayout(new FlowLayout( ));
        isPhDPanel.setVisible(false);
        addStudentPanel.add(isPhDPanel);
        //->->-> the isPhD label for the isPhD
        JLabel isPhDLabel = new JLabel("isPhD: ");
        isPhDPanel.add(isPhDLabel);
        //->->-> the combo box for the isPhD
        String[] isPhdChoice = {"no", "yes"};
        JComboBox isPhDComboBox = new JComboBox(isPhdChoice);
        isPhDComboBox.setSelectedIndex(0);
        isPhDComboBox.addActionListener(new IsPhdListener( ));
        isPhDPanel.add(isPhDComboBox);

        //->-> the undergraduateSchool entree for the Add student Panel
        undergraduateSchoolPanel = new JPanel( );
        undergraduateSchoolPanel.setLayout(new FlowLayout( ));
        undergraduateSchoolPanel.setVisible(false);
        addStudentPanel.add(undergraduateSchoolPanel);
        //->->-> the undergraduateSchool label for the undergraduateSchool
        JLabel undergraduateSchoolLabel = new JLabel("Undergraduate School Name: ");
        undergraduateSchoolPanel.add(undergraduateSchoolLabel);
        //->->-> the text box for the undergraduateSchool
        undergraduateSchoolText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        undergraduateSchoolPanel.add(undergraduateSchoolText);


        //->Add student Panel buttons layout for entrees
        JPanel addStudentButtonsPanel = new JPanel( );
        addStudentButtonsPanel.setLayout(new BoxLayout(addStudentButtonsPanel, BoxLayout.X_AXIS));
        //addStudentButtonsPanel.setVisible(true);
        addStudentPanel.add(addStudentButtonsPanel);

        //->->the reset button for the add students window
        JButton studentResetButton = new JButton("Reset");
        studentResetButton.addActionListener(new StudentResetButtonListener( ));
        addStudentButtonsPanel.add(studentResetButton);

        //->->the add button for the add students window
        JButton studentAddButton = new JButton("Add");
        studentAddButton.addActionListener(new StudentAddButtonListener( ));
        addStudentButtonsPanel.add(studentAddButton);


        //->Add student messages layout for entrees
        JPanel addStudentMsgFlowPanel = new JPanel();
        addStudentLabelFlowPanel.setLayout(new FlowLayout());
        addStudentPanel.add(addStudentMsgFlowPanel);
        //->->label for Messaages
        addStudentMsgFlowPanel.add(new JLabel("<html><br/>Messages<html>"));
        //->->text area for messages
        JPanel studentTextPanel = new JPanel( );
        //textPanel.setBackground(Color.BLUE);
        studmemoDisplay = new JTextArea(5, 30);
        studmemoDisplay.setBackground(Color.WHITE);
        studmemoDisplay.setEditable(false);
        JScrollPane studscrolledText = new JScrollPane(studmemoDisplay);
        studscrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        studscrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        studentTextPanel.add(studscrolledText);
        addStudentPanel.add(studentTextPanel);




        //########### Print all
        printInfoPanel = new JPanel( );
        printInfoPanel.setLayout(new BoxLayout(printInfoPanel, BoxLayout.Y_AXIS));
        printInfoPanel.setVisible(false);
        add(printInfoPanel, BorderLayout.SOUTH);
        //->->label for print info
        JPanel printInfoLabelFlowPanel = new JPanel();
        printInfoLabelFlowPanel.setLayout(new FlowLayout());
        printInfoPanel.add(printInfoLabelFlowPanel);
        JLabel printInfoLabel = new JLabel("Info on all students", JLabel. CENTER);
        printInfoLabelFlowPanel.add(printInfoLabel);
        //->->text area for printInfo
        JPanel PItextPanel = new JPanel( ); 
        //textPanel.setBackground(Color.BLUE);
        memoDisplay = new JTextArea(20, 30);
        memoDisplay.setBackground(Color.WHITE);
        memoDisplay.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(memoDisplay);
        scrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        PItextPanel.add(scrolledText);
        printInfoPanel.add(PItextPanel);



        //########## print student average and no. of students
        printAvgPanel = new JPanel();
        //printAvgPanel.setLayout(new BorderLayout());
        //addPanel.setBackground(Color.DARK_GRAY);
        printAvgPanel.setVisible(false);
        add(printAvgPanel);
        avgStringLabel= new JLabel("<html>Average of the average grades: 0 <br/>Total number of students: 0</html>");
        printAvgPanel.add(avgStringLabel);
            
        
        //########## read input file
        readFilePanel = new JPanel();
        readFilePanel.setLayout(new BoxLayout(readFilePanel, BoxLayout.Y_AXIS));
        readFilePanel.setVisible(false);
        add(readFilePanel);

        //->geting the filename
        JPanel readFileNamePanel = new JPanel();
        readFileNamePanel.setLayout(new FlowLayout());
        readFilePanel.add(readFileNamePanel);
        JLabel readFileNameLabel = new JLabel("Enter input file name: ");
        readFileNamePanel.add(readFileNameLabel);
        //->-> the text box for the readFileName
        readFileNameText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        readFileNamePanel.add(readFileNameText);

        JButton readFileButton = new JButton("Read File"); 
        readFileButton.addActionListener(new ReadFileButtonListener());
        JPanel readFileButtonFlowPanel = new JPanel();
        readFileButtonFlowPanel.setLayout(new FlowLayout());
        readFilePanel.add(readFileButtonFlowPanel);
        readFileButtonFlowPanel.add(readFileButton); 

        //-> message box in case of errors
        //->->label for Messages
        JLabel ifmessagesLabel = new JLabel("Messages");
        JPanel ifmessagesFlowPanel = new JPanel();
        ifmessagesFlowPanel.setLayout(new FlowLayout());
        readFilePanel.add(ifmessagesFlowPanel);
        ifmessagesFlowPanel.add(ifmessagesLabel);
        //->->text area for messages
        JPanel iftextPanel = new JPanel( ); 
        //iftextPanel.setBackground(Color.BLUE);
        ifmemoDisplay = new JTextArea(5, 30);
        ifmemoDisplay.setBackground(Color.WHITE);
        ifmemoDisplay.setEditable(false);
        JScrollPane ifScrolledText = new JScrollPane(ifmemoDisplay);
        ifScrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ifScrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        iftextPanel.add(ifScrolledText);
        readFilePanel.add(iftextPanel);



        //########## file data dump
        fileDumpPanel = new JPanel();
        fileDumpPanel.setLayout(new BoxLayout(fileDumpPanel, BoxLayout.Y_AXIS));
        fileDumpPanel.setVisible(false);
        add(fileDumpPanel);

        //->geting the filename
        JPanel fileDumpNamePanel = new JPanel();
        fileDumpNamePanel.setLayout(new FlowLayout());
        fileDumpPanel.add(fileDumpNamePanel);
        JLabel fileDumpNameLabel = new JLabel(" Enter file name to store the Student List: ");
        fileDumpNamePanel.add(fileDumpNameLabel);
        //->-> the text box for the fileDumpName
        fileDumpNameText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        fileDumpNamePanel.add(fileDumpNameText);

        JButton fileDumpButton = new JButton("write File"); 
        fileDumpButton.addActionListener(new fileDumpButtonListener());
        JPanel fileDumpFlowPanel = new JPanel();
        fileDumpFlowPanel.setLayout(new FlowLayout());
        fileDumpPanel.add(fileDumpFlowPanel);
        fileDumpFlowPanel.add(fileDumpButton); 

        //-> message box in case of errors
        //->->label for Messaages
        JLabel ofmessagesLabel = new JLabel("Messages");
        JPanel ofmessagesFlowPanel = new JPanel();
        ofmessagesFlowPanel.setLayout(new FlowLayout());
        fileDumpPanel.add(ofmessagesFlowPanel);
        ofmessagesFlowPanel.add(ofmessagesLabel);
        //->->text area for messages
        JPanel oftextPanel = new JPanel( ); 
        //oftextPanel.setBackground(Color.BLUE);
        ofmemoDisplay = new JTextArea(5, 30);
        ofmemoDisplay.setBackground(Color.WHITE);
        ofmemoDisplay.setEditable(false);
        JScrollPane ofScrolledText = new JScrollPane(ofmemoDisplay);
        ofScrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ofScrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        oftextPanel.add(ofScrolledText);
        fileDumpPanel.add(oftextPanel);



        //########## Lookup via a hashmap key
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setVisible(false);
        add(searchPanel);

        //->geting the filename
        JPanel searchNamePanel = new JPanel();
        searchNamePanel.setLayout(new FlowLayout());
        searchPanel.add(searchNamePanel);
        JLabel searchNameLabel = new JLabel(" Enter students last name: ");
        searchNamePanel.add(searchNameLabel);
        //->-> the text box for the searchName
        searchNameText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        searchNamePanel.add(searchNameText);

        //->geting the filename
        JPanel searchProgramPanel = new JPanel();
        searchProgramPanel.setLayout(new FlowLayout());
        searchPanel.add(searchProgramPanel);
        JLabel searchProgramLabel = new JLabel(" Enter students program: ");
        searchProgramPanel.add(searchProgramLabel);
        //->-> the text box for the searchProgram
        searchProgramText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        searchProgramPanel.add(searchProgramText);

        //->geting the filename
        JPanel searchYearPanel = new JPanel();
        searchYearPanel.setLayout(new FlowLayout());
        searchPanel.add(searchYearPanel);
        JLabel searchYearLabel = new JLabel(" Enter students year: ");
        searchYearPanel.add(searchYearLabel);
        //->-> the text box for the searchYear
        searchYearText = new JTextField(15);
        //String inputString = undergraduateSchoolText.getText();
        searchYearPanel.add(searchYearText);

        JButton searchButton = new JButton("Search"); 
        searchButton.addActionListener(new SearchHKeyListener());
        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new FlowLayout());
        searchPanel.add(searchButtonPanel);
        searchButtonPanel.add(searchButton); 

        //-> message box in case of errors
        //->->label for Messaages
        JLabel searchMessagesLabel = new JLabel("Messages");
        JPanel searchMessagesFlowPanel = new JPanel();
        searchMessagesFlowPanel.setLayout(new FlowLayout());
        searchPanel.add(searchMessagesFlowPanel);
        searchMessagesFlowPanel.add(searchMessagesLabel);
        //->->text area for messages
        JPanel searchTextPanel = new JPanel( ); 
        //oftextPanel.setBackground(Color.BLUE);
        searchMemoDisplay = new JTextArea(10, 30);
        searchMemoDisplay.setBackground(Color.WHITE);
        searchMemoDisplay.setEditable(false);
        JScrollPane searchScrolledText = new JScrollPane(searchMemoDisplay);
        searchScrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchScrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        searchTextPanel.add(searchScrolledText);
        searchPanel.add(searchTextPanel);


        JMenu commandsMenu = new JMenu("Commands");

        JMenuItem addChoice = new JMenuItem("add Student");
        addChoice.addActionListener(new AddListener( ));
        commandsMenu.add(addChoice);

        JMenuItem printInfo = new JMenuItem("all student info");
        printInfo.addActionListener(new printInfoListener( ));
        commandsMenu.add(printInfo);

        JMenuItem printAvg = new JMenuItem("student average and no. of students");
        printAvg.addActionListener(new printAvgListener( ));
        commandsMenu.add(printAvg);

        JMenuItem readFile = new JMenuItem("read input file");
        readFile.addActionListener(new ReadFileListener( ));
        commandsMenu.add(readFile);
        
        JMenuItem fileDump = new JMenuItem("file data dump");
        fileDump.addActionListener(new fileDumpListener( ));
        commandsMenu.add(fileDump);

        JMenuItem searchChoice = new JMenuItem("search for Student");
        searchChoice.addActionListener(new SearchListener( ));
        commandsMenu.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("quit");
        quitChoice.addActionListener(new QuitListener( ));
        commandsMenu.add(quitChoice);

        JMenuBar bar = new JMenuBar( );
        bar.add(commandsMenu);
        setJMenuBar(bar);
    }
}