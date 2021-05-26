 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-DataRetrive.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRetrive {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/store";
    static final String USER = "root";
    static final String PASS = "";

    // public static void main(String[] args) throws Exception {
    // var data = RetriveDataFromDatabase("select * from mobile", new
    // PrintWriter(System.out));

    // }

    public static List<Mobile> RetriveDataFromDatabase(String sql, PrintWriter pw) {
        int qty;
        ArrayList<Mobile> ar = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("model");
                String company = rs.getString("company");
                Double price = rs.getDouble("price");
                try {
                    qty = rs.getInt("qty");
                } catch (Exception e) {
                    qty = 0;
                }
                ar.add(new Mobile(id, name, company, price, qty));
            }
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
        return ar;
    }
}
