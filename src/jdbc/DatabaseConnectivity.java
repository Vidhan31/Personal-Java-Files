package jdbc;// First download official mysql connector and then set jar file path in environment variable then import it here.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

class DBOperations {
    private static int studentRollNo;
    private static String studentName;

    private static void userInput() throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Student Roll Number : ");
        studentRollNo = Integer.parseInt(input.readLine());
        System.out.println("Enter Student's name : ");
        studentName = input.readLine();
    }

    public static void DisplayResult(Connection conn) throws SQLException {

        //sub interface of statement interface.
        //prepared statement improves performance becuz it compiles query only once.
        //Used mostly to manipulate and set data.  no SQL Injection attack.
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM student_info");


        //RS is used to display results.
        //object closes when statement object closes.
        ResultSet rs = pst.executeQuery(); //executes query


        //moves the cursor to next row until there is none (i.e. NULL.) and returns false
        //coloumn name row at the top is also considered.
        while (rs.next()) {
            //used to get information about the types and properties of the columns in a ResultSet object.
            ResultSetMetaData rsMeta = rs.getMetaData();
            int coloumnNumbers = rsMeta.getColumnCount();
            for (int i = 1; i <= coloumnNumbers; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println("\n");


            //Similar to ResultSetMetaData, except it provides for database and drivers.
            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println("UserName: " + dbmd.getUserName());
            System.out.println("Database Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbmd.getDatabaseProductVersion());
        }
        pst.close();

    }

    public static void InsertData(Connection conn) throws SQLException, IOException {

        userInput();

        //PreparedStatement pst2 = conn.prepareStatement("INSERT INTO student_info VALUES ('Cox', 3, NULL, NULL)");

        //Order must be same as in DB. You need to fill every field/coloumn otherwise error is thrown.
        PreparedStatement pst = conn.prepareStatement("INSERT INTO student_info VALUES(?,?,NULL)");


        pst.setString(1, studentName);
        pst.setInt(2, studentRollNo);


        //returns number of rows affected so we store it in integer, not object.
        int rowsAffected = pst.executeUpdate();
        System.out.println("Number of rows affected are : " + rowsAffected);

    }

    public static void UpdateData(Connection conn) throws SQLException, IOException {

        userInput();

        PreparedStatement pst = conn.prepareStatement("UPDATE student_info SET stud_name = ? WHERE stud_roll = ?");
        pst.setString(1, studentName);
        pst.setInt(2, studentRollNo);

        int rowsAffected = pst.executeUpdate();
    }

    public static void DeleteData(Connection conn) throws SQLException, IOException {

        userInput();

        PreparedStatement pst = conn.prepareStatement("DELETE FROM student_info WHERE stud_roll = ?");
        pst.setInt(1, studentRollNo);

        int rowsAffected = pst.executeUpdate();
    }
}

public class DatabaseConnectivity {
    public static void main(String[] args) throws Exception {

        //jdbc = driver, mysql = database, localhost = server, 3306 = port number.
        String URL = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "";

        //Class.forname helps us to initialize static block without creating an object of that class.
        //optional since JDBC 4.0
        Class.forName("java.sql.DriverManager");


        //Create connection class ref. var and store connection details inside it.
        //DriverManager Class provides interface between users and drivers and help connecting to database.
        //Connection interface provide many methods for taking in queries.
        Connection conn = DriverManager.getConnection(URL, username, password);

        try {
        //DBOperations.InsertData(conn);
        //DBOperations.UpdateData(conn);
        //DBOperations.DeleteData(conn);
        DBOperations.DisplayResult(conn);
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }

        //If you don't close() the connection, after a few seconds, you won't have any connection available anymore.
        //may also cause memory leakage issues.
        conn.close();
    }
}
