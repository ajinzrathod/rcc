 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-Cart.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.List;

public class Cart extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        pw.println(
                "<!Doctype HTML><html> <head> <title> Cart </title> <style> table{ border-spacing:0px; } td{ text-align:center; } "
                        + "</style> </head> <body><form action=\"DeleteFromDatabase\" method=\"get\"> <table border=1>"
                        + " <tr> <th> Name </th> <th> Company </th> <th> Price </th>  <th> Quantity </th> <th> Action </th></tr>");
        String sql = "select a.*,b.qty from mobile a, cart b where a.id = b.mobileid";
        try {
            List<Mobile> cartMobile = DataRetrive.RetriveDataFromDatabase(sql, pw);
            cartMobile.stream()
                    .forEach(m -> pw.println("<tr> <td>" + m.model + " </td><td>" + m.company + "</td><td>" + m.price
                            + "</td><td>" + m.qty + "</td><td><input type=\"radio\" name=\"phone\" value=\"" + m.id
                            + "\"> </td> </tr>"));
        } catch (Exception e) {
            pw.println(e.getMessage());
        }
        pw.println(
                " </table> <input type=\"submit\" value=\"Remove From Cart\"></form> <a href=\"Index\">Show Mobiles</a> </body> </html>");
        pw.close();
    }
}
