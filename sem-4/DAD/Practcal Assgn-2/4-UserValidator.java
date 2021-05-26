 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      4-UserValidator.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;

public class UserValidator {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/company";
    static final String USER = "root";
    static final String PASS = "";

    // public static void main(String[] args) {
    // boolean state = ValidateUser("pradip", "pradip");
    // System.out.println(state);
    // }

    public static void ValidateUser(String username, String password, PrintWriter pw, HttpSession session) {

        String sql = "select * from employees where name=\'" + username + "\' AND password=\'" + password + "\'";
        int count = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                session.setAttribute("name", name);
                session.setAttribute("address", address);
                session.setAttribute("date", java.time.LocalDate.now());
                session.setAttribute("time", java.time.LocalTime.now());
            }
            rs.close();
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
    }
}
