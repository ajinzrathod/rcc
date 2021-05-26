 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      1-UserValidator.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.*;
import java.io.PrintWriter;

public class UserValidator {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/emp";
    static final String USER = "root";
    static final String PASS = "";

    // public static void main(String[] args) {
    // boolean state = ValidateUser("pradip", "pradip");
    // System.out.println(state);
    // }

    public static boolean ValidateUser(String username, String password, PrintWriter pw) {

        String sql = "select count(*) as count from employees where name=\'" + username + "\' AND password=\'"
                + password + "\'";
        int count = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            count = rs.getInt("count");
            rs.close();
        } catch (Exception e) {
            pw.println(e.getMessage());
        }

        System.out.println(count);
        return count > 0;
    }
}
