package L4;

public class Student {
    private String lastName;
    private String program;
    private int year;
    private double averageGrade = 0.0;

    public Student(String Lastname, String program, String year) throws Exception {
        this.lastName = Lastname;
        this.program = program;
        try {
            this.year = Integer.parseInt(year);
        } catch (Exception e) {
            //System.out.println("Error: the year argument cannot be turned to an integer");
            throw new Exception("error could not convert year to integer");
        }
        averageGrade = 0.0;
    }

    public Student(String Lastname, String program, String year, double grade) throws Exception {
        this.lastName = Lastname;
        this.program = program;
        try {
            this.year = Integer.parseInt(year);
        } catch (Exception e) {
            //System.out.println("Error: the year argument cannot be turned to an integer");
            throw new Exception("error could not convert year to integer");
        }
        if (grade>=0 && grade<=100) {
            averageGrade = grade;
        } else {
            throw new Exception("Fatal error: average grade not in range of 0-100");
        }
        
    }

    public void setGrade(double grd) {
        if (grd> 100 || grd < 0) {
            System.out.println("Error: the grade should be between 0 and 100 (inclusive)");
        } else {
            this.averageGrade = grd;
        }
    }

    public double getGrade() {
        return averageGrade;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }

    public String getProgram() {
        return program;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return "Last Name: " + this.getLastName() +
               "\nProgram: " + this.getProgram() +
               "\nYear: " + this.getYear() +
               "\nAverage Grade: " + this.getGrade() + "\n";
    }

    public String toStringInLine() {
        return (this.getLastName() + " " + this.getProgram() + " " + this.getYear() + " " + this.getGrade());
    }

    public boolean isEqual(String str1) {
        return program.equals(str1);
    }

    public boolean equals (Object tempObject) {
        return this == tempObject;
    }
}