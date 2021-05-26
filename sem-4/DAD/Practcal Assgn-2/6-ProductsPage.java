 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-ProductsPage.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.util.List;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProductsPage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("-- creating new session in the servlet --");
            session = request.getSession(true);
            System.out.println("-- session created in the servlet --");
        }

        List<Product> products = Database.fetchProducts(pw);

        pw.print("<form method='post' action='AddToCart'>");
        pw.print("<table border='1' style='border-collapse: collapse;'>");

        pw.print("<tr><th></th><th>Name</th><th>Description</th><th>Price</th></tr>");

        products.stream().map(p -> {
            return "<tr>" + "<td><input type='checkbox' name='addToCart' value='" + p.id + "'></td>" + "<td>" + p.name
                    + "</td>" + "<td>" + p.description + "</td>" + "<td>Rs. " + p.price + "</td>" + "</tr>";
        }).forEach(pw::print);

        pw.print("</table>");
        pw.print("<input type='submit' value='Add to Cart'>");
        pw.print("</form>");

        pw.close();
    }
}
