package BuilderPattern;

public class StudentClassBuilder {

    private String studentName;
    private Integer studentRollNo;

    public StudentClassBuilder setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public StudentClassBuilder setStudentRollNo(String studentRollNo) {
        this.studentRollNo = Integer.valueOf(studentRollNo);
        return this;
    }

    public Student getStudentDetails() {
        return new Student(studentName, studentRollNo);
    }
}
