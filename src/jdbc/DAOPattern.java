package jdbc;//Usage of wrapper class Integer, Double, etc makes sense because they can store NULL which is required.
//Create a new instance or pass values to constructor for SQL queries when you want to do more than just executing a
//query. This way you can manipulate that object's data.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

interface DatabaseOperations {

    void startProgram() throws Exception;

    boolean insertStudentData(Student studentObject) throws Exception;

    boolean updateStudentData() throws Exception;

    boolean deleteStudentData(Student studentObject) throws Exception;

    void displayStudentData() throws Exception;
}

class StudentDAO implements DatabaseOperations {

    Connection connect;

    public StudentDAO(Connection connect) {
        this.connect = connect;
    }

    public void startProgram() throws Exception {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        System.out.println("Operations");
        System.out.println("1. Insert \n2. Display \n3. Update \n4. Delete ");
        System.out.println("Enter your choice : ");

        try {
            choice = Integer.parseInt(input.readLine());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } finally {
            input.close();
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
                    if (this.updateStudentData()) {
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
    }

    @Override
    public boolean insertStudentData(Student studentObject) throws Exception {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student name : ");
            studentObject.setStudentName(input.readLine());
            System.out.println("Enter student roll number : ");
            studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        String insertQuery = "INSERT INTO student_info VALUES (?,?, NULL)";
        int rowsAffected;
        try (PreparedStatement pst = connect.prepareStatement(insertQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pst.setString(1, studentObject.getStudentName());
            pst.setInt(2, studentObject.getStudentRollNo());
            rowsAffected = pst.executeUpdate();
        } finally {
            connect.close();
        }
        return rowsAffected >= 1;
    }

    @Override
    public boolean updateStudentData() throws Exception { //tried diff approach than other methods.

        Student studentObject = new Student();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student name : ");
            studentObject.setStudentName(input.readLine());
            System.out.println("Enter student roll number :");
            studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        int rowsAffected;
        try (PreparedStatement query = connect.prepareStatement("UPDATE student_info SET stud_name = ? WHERE stud_roll = ?")) {
            query.setString(1, studentObject.getStudentName());
            query.setInt(2, studentObject.getStudentRollNo());
            rowsAffected = query.executeUpdate();
            query.close();
        } finally {
            connect.close();
        }
        return rowsAffected >= 1;
    }

    @Override
    public boolean deleteStudentData(Student studentObject) throws Exception {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student roll number : ");
            studentObject.setStudentRollNo(Integer.parseInt(input.readLine()));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


        int rowsAffected;
        try (PreparedStatement query = connect.prepareStatement("DELETE FROM student_info WHERE stud_roll = ?")) {
            query.setInt(1, studentObject.getStudentRollNo());
            rowsAffected = query.executeUpdate();
        } finally {
            connect.close();
        }
        return rowsAffected >= 1;
    }

    @Override
    public void displayStudentData() throws Exception {

        ResultSet result = null;
        PreparedStatement query = null;
        try {
            query = connect.prepareStatement("SELECT * FROM student_info");
            result = query.executeQuery();
            while (result.next()) {
                //used to get information about the types and properties of the columns in a ResultSet object.
                ResultSetMetaData rsMeta = result.getMetaData();
                int columnNumbers = rsMeta.getColumnCount();
                for (int i = 1; i <= columnNumbers; i++) {
                    System.out.print(result.getString(i) + " ");
                }
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert result != null;
            result.close();
        }
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

    public Connection getConnection() throws SQLException {

        String URL = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "";

        try (Connection connect = DriverManager.getConnection(URL, username, password)) {
            return connect;
        }
    }
}

public class DAOPattern {

    public static void main(String[] args) {

        DatabaseOperations object = null;
        try {
            object = new StudentDAO(new ManageDatabaseConnection().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert object != null;
            object.startProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}