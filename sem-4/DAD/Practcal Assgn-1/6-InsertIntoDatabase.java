 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-InsertIntoDatabase.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class InsertIntoDatabase extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/store";
    static final String USER = "root";
    static final String PASS = "";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        int res = 0;
        String sql;
        int id = Integer.parseInt(request.getParameter("phone"));
        // request.getParameterValues();
        String checkCart = "select count(mobileid) from cart where mobileid=" + id;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();) {
            Class.forName(JDBC_DRIVER);
            ResultSet rs = stmt.executeQuery(checkCart);
            rs.next();
            int count = rs.getInt("count(mobileid)");
            rs.close();
            if (count > 0) {
                sql = "update cart set qty=qty+1 where mobileid = (" + id + ")";
                res = stmt.executeUpdate(sql);
            } else {
                sql = "insert into cart(mobileid,qty) values(" + id + ",1)";
                res = stmt.executeUpdate(sql);
            }
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
        pw.println(
                "<!Doctype HTML><html> <head> <title> Database Status </title> </head> <body> <form action=\"Cart\" method=\"get\">");
        if (res > 0)
            pw.println("<p>Added to Cart....</p>");
        else
            pw.println("<p>Failed to add....</p>");

        pw.println("<input type=\"submit\" value=\"Cart\"> </form> </body> </html>");

        pw.close();
    }
}
