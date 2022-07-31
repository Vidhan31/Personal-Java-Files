package BuilderPattern;

public class Student {

    private String studentName;
    private Integer studentRollNo;

    public Student(String studentName, Integer studentRollNo) {
        this.studentName = studentName;
        this.studentRollNo = studentRollNo;
    }

    @Override
    public String toString() {
        return ("Student name : "+ studentName + "\nStudent Roll No : " + studentRollNo);
    }
}
