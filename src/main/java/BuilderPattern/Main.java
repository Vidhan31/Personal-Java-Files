package BuilderPattern;

public class Main {

    public static void main(String[] args) {

        Student student = new StudentClassBuilder().setStudentName("Vidhan").getStudentDetails();
        System.out.println(student.toString());
    }
}
