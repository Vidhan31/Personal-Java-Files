package jdbc.apache;//Usage of wrapper class Integer, Double, etc makes sense because they can store NULL which is required.
//Create a new instance or pass values to constructor for SQL queries when you want to do more than just executing a
//query. This way you can manipulate that object's data.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

interface DatabaseOperations {

    void startProgram() throws Exception;

    boolean insertStudentData() throws Exception;

    boolean updateStudentData() throws Exception;

    boolean deleteStudentData() throws Exception;

    void displayStudentData() throws Exception;
}

class StudentDAO implements DatabaseOperations {

    Connection connect = null;

    @Override
    public void startProgram() throws Exception {

        int choice = 0;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        while (choice != 5) {

            System.out.println("Operations");
            System.out.println("1. Insert \n2. Display \n3. Update \n4. Delete \n5. Exit");
            System.out.println("Enter your choice : ");
            choice = Integer.parseInt(input.readLine());

            switch (choice) {

                case 1:
                    if (this.insertStudentData()) {
                        System.out.println("Inserted data successfully.");
                    }
                    break;

                case 2:
                    this.displayStudentData();
                    break;

                case 3:
                    if (this.updateStudentData()) {
                        System.out.println("Updated data successfully.");
                    }
                    break;

                case 4:
                    if (this.deleteStudentData()) {
                        System.out.println("Deleted data successfully.");
                    }
                    break;

                case 5:
                    System.exit(1);

                default:
                    System.out.println("Invalid choice. Try again...");
                    break;
            }
        }
    }

    @Override
    public boolean insertStudentData() throws Exception {

        String studentName = null;
        Integer studentRollNo = null;
        Student studentObject = null;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student name : ");
            studentName = input.readLine();
            System.out.println("Enter student roll number : ");
            studentRollNo = Integer.parseInt(input.readLine());
            studentObject = new Student(studentName, studentRollNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String insertQuery = "INSERT INTO student_info VALUES (?,?, NULL)";
        int rowsAffected = 0;
        try (Connection connection = connect = ConnectionPool.getDataSource().getConnection()) {
            try (PreparedStatement pst = connect.prepareStatement(insertQuery,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                pst.setString(1, studentObject.getStudentName());
                pst.setInt(2, studentObject.getStudentRollNo());
                rowsAffected = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected >= 1;
    }

    @Override
    public boolean updateStudentData() throws Exception {

        String studentName = null;
        Integer studentRollNo = null;
        Student studentObject = null;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student name : ");
            studentName = input.readLine();
            System.out.println("Enter student roll number : ");
            studentRollNo = Integer.parseInt(input.readLine());
            studentObject = new Student(studentName, studentRollNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String updateQuery = "UPDATE student_info SET stud_name = ? WHERE stud_roll = ?";
        int rowsAffected = 0;
        try (Connection connection = connect = ConnectionPool.getDataSource().getConnection()) {
            try (PreparedStatement query = connect.prepareStatement(updateQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                query.setString(1, studentObject.getStudentName());
                query.setInt(2, studentObject.getStudentRollNo());
                rowsAffected = query.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected >= 1;
    }

    @Override
    public boolean deleteStudentData() throws Exception {

        Integer studentRollNo = null;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter student roll number : ");
            studentRollNo = Integer.parseInt(input.readLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String deleteQuery = "DELETE FROM student_info WHERE stud_roll = ?";
        int rowsAffected = 0;
        try (Connection connection = connect = ConnectionPool.getDataSource().getConnection()) {
            try (PreparedStatement query = connect.prepareStatement(deleteQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                query.setInt(1, studentRollNo);
                rowsAffected = query.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected >= 1;
    }

    @Override
    public void displayStudentData() throws Exception {

        try (Connection connection = connect = ConnectionPool.getDataSource().getConnection()) {
            try (PreparedStatement query = connect.prepareStatement("SELECT * FROM student_info", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                try (ResultSet result = query.executeQuery()) {
                    result.afterLast(); //basically points to row after last row. Without this it will point to row before first row.
                    while (result.previous()) {
                        System.out.print(result.getInt("stud_roll") + " " + result.getString("stud_name"));
                        System.out.println("\n");
                    }
                    System.out.println("Connect = " + connect.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class StudentClassImpl {

    public static void main(String[] args) {

        try {
            DatabaseOperations object = new StudentDAO();
            object.startProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}