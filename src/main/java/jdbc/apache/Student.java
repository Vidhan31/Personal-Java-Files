package jdbc.apache;

public class Student {

    private String studentName;
    private Integer studentRollNo;

    public Student(String studentName, Integer studentRollNo) {
        this.studentName = studentName;
        this.studentRollNo = studentRollNo;
    }

    public Student(String studentName) {
        this (studentName, null);
    }

    public Student(Integer studentRollNo) {
        this(null, studentRollNo);
    }

    public String getStudentName() {
        return studentName;
    }

    public Integer getStudentRollNo() {
        return studentRollNo;
    }
}
