 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      5-StudentDataSave.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.*;
import java.io.PrintWriter;

public class StudentDataSave {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/college";
    static final String USER = "root";
    static final String PASS = "";

    public static boolean SaveStudent(String name, String currentClass, String gender, long number, PrintWriter pw) {
        int result = 0;
        String sql = "insert into students(name,class,gender,number) values(\'" + name + "\',\'" + currentClass
                + "\',\'" + gender + "\'," + number + ")";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
        return result > 0;
    }
}
