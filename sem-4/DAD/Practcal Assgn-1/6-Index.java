 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-Index.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.List;

public class Index extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        pw.println(
                "<!Doctype HTML><html> <head> <title> Mobiles </title> <style> table{ border-spacing:0px; } td{ text-align:center; }"
                        + " </style> </head> <body> <form action=\"InsertIntoDatabase\" method=\"get\"> <table border=1>"
                        + " <tr> <th> Name </th> <th> Company </th> <th> Price </th> <th> Action </th></tr>");

        String sql = "select * from mobile";
        try {
            List<Mobile> indexResultSet = DataRetrive.RetriveDataFromDatabase(sql, pw);
            indexResultSet.stream()
                    .forEach(m -> pw.println("<tr> <td>" + m.model + " </td><td>" + m.company + "</td><td>" + m.price
                            + "</td><td><input type=\"radio\" name=\"phone\" value=\"" + m.id + "\"> </td> </tr>"));
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
        pw.println(
                " </table> <input type=\"submit\" value=\"Add To Cart\"> </form> <a href=\"Cart\">Cart</a> </body> </html>");
        pw.close();
    }
}
