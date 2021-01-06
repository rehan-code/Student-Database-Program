package L4;

public class GraduateStudent extends Student{
    private String supervisor;
    private boolean isPhD;
    private String undergraduateSchool;

    public GraduateStudent(String Lastname, String program, String year) throws Exception {
        super(Lastname, program, year);
        isPhD = false;
    }

    public GraduateStudent(String Lastname, String program, String year, double grade, String supervisor, boolean isPhD, String undergraduateSchool) throws Exception {
        super(Lastname, program, year, grade);
        this.supervisor = supervisor;
        this.isPhD = false;
        this.isPhD = isPhD;
        this.undergraduateSchool = undergraduateSchool;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public void setIsPhD(String isPhD) {
        this.isPhD = Boolean.parseBoolean(isPhD);
    }

    public void setUndergraduateSchool(String UndergraduateSchool) {
        this.undergraduateSchool = UndergraduateSchool;
    }

    public String getSupervisor() {
        return supervisor;
    }
    
    public boolean getIsPhD() {
        return isPhD;
    }

    public String getUndergraduateSchool() {
        return undergraduateSchool;
    }

    @Override
    public String toString() {
        String tempString = super.toString();
        if (isPhD) {
            tempString = tempString + "PhD";
        } else {
            tempString = tempString + "Masters";
        }
        tempString = tempString + "\nSupervisor: " + this.getSupervisor() + 
                    "\nUndergraduate School: " + this.getUndergraduateSchool() + "\n";
        return tempString;
    }

    public String toStringInLine() {
        String tempString = super.toStringInLine();
        tempString = tempString + " " + this.supervisor + " ";
        if (this.getIsPhD() == true) {
            tempString = tempString + "1 ";
        } else {
            tempString = tempString +"0 ";
        }
        return (tempString + this.getUndergraduateSchool());
    }

    @Override
    public boolean equals (Object tempObject) {
        return this == tempObject;
    }
}