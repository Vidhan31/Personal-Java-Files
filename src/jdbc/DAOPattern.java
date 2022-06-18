package jdbc;//Usage of wrapper class Integer, Double, etc makes sense because they can store NULL which is required in DBMS.
//Create a new instance or pass values to constructor for SQL queries when you want to do more than just executing a
//query. This way you can manipulate that object's data.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

interface CRUDOperations {

    void startProgram() throws Exception;

    boolean insertStudentData(Student studentObject) throws Exception;

    boolean updateStudentData(Student studentObject) throws Exception;

    boolean deleteStudentData(Student studentObject) throws Exception;

    void displayStudentData() throws Exception;
}

class StudentDAO implements CRUDOperations {

    Connection connect = null;

    public StudentDAO(Connection connect) {
        this.connect = connect;
    }

    public void startProgram() throws Exception {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        System.out.println("Operations");
        System.out.println("1. Insert \n2. Display \n3. Update \n4. Delete");
        System.out.println("Enter your choice : ");

        try {
            choice = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (choice) {

            case 1:
                try {
                    if (this.insertStudentData(new Student())) {
                        System.out.println("Inserted data successfully.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 2:
                try {
                    this.displayStudentData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 3:
                try {
                    if (this.updateStudentData(new Student())) {
                        System.out.println("Updated data successfully.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            case 4:
                try {
                    if (this.deleteStudentData(new Student())) {
                        System.out.println("Deleted data successfully.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;

            default:
                System.out.println("Invalid choice. Try again...");
                break;
        }

        input.close();
    }

    @Override
    public boolean insertStudentData(Student studentObject) throws Exception {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter student name : ");
        studentObject.setStudentName(input.readLine());
        System.out.println("Enter student roll number : ");
        studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        input.close();

        PreparedStatement query = connect.prepareStatement("INSERT INTO student_info VALUES (?,?, NULL)");
        query.setString(1, studentObject.getStudentName());
        query.setInt(2, studentObject.getStudentRollNo());
        int rowsAffected = query.executeUpdate();
        query.close();
        connect.close();
        return rowsAffected >= 1;
    }

    @Override
    public boolean updateStudentData(Student studentObject) throws Exception {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter student name : ");
        studentObject.setStudentName(input.readLine());
        System.out.println("Enter student roll number : ");
        studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        input.close();

        PreparedStatement query = connect.prepareStatement("UPDATE student_info SET stud_name = ? WHERE stud_roll = ?");
        query.setString(1, studentObject.getStudentName());
        query.setInt(2, studentObject.getStudentRollNo());
        int rowsAffected = query.executeUpdate();
        query.close();
        connect.close();
        return rowsAffected >= 1;
    }

    @Override
    public boolean deleteStudentData(Student studentObject) throws Exception {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter student roll number : ");
        studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        input.close();

        PreparedStatement query = connect.prepareStatement("DELETE FROM student_info WHERE stud_roll = ?");
        query.setInt(1, studentObject.getStudentRollNo());
        int rowsAffected = query.executeUpdate();
        query.close();
        connect.close();
        return rowsAffected >= 1;
    }

    @Override
    public void displayStudentData() throws Exception {

        PreparedStatement query = connect.prepareStatement("SELECT * FROM student_info");
        ResultSet result = query.executeQuery();
        while (result.next()) {
            //used to get information about the types and properties of the columns in a ResultSet object.
            ResultSetMetaData rsMeta = result.getMetaData();
            int columnNumbers = rsMeta.getColumnCount();
            for (int i = 1; i <= columnNumbers; i++) {
                System.out.print(result.getString(i) + " ");
            }
            System.out.println("\n");
        }
        result.close();
        connect.close();
    }
}

class Student { //POJO class

    private String studentName;
    private Integer studentRollNo;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(Integer studentRollNo) {
        this.studentRollNo = studentRollNo;
    }
}

class ManageDatabaseConnection {

    Connection connect = null;

    public Connection getConnection() throws Exception {

        String URL = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "";

        try {
            connect = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connect;
    }
}

public class DAOPattern {

    public static void main(String[] args) {

        CRUDOperations object = null;

        try {
            object = new StudentDAO(new ManageDatabaseConnection().getConnection());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            object.startProgram();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}