 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-ShopCart.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class ShopCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        Cart cart = Database.fetchCart(pw);
        pw.print("<form action='RemoveFromCart' method='post'>");
        pw.print("<table border='1' style='border-collapse: collapse;'>");
        pw.print("<tr><th>Product</th><th>Quantity</th><th></th></tr>");

        cart.items().stream().forEach(item -> {
            pw.print("<tr>");
            pw.print("<td>" + item.product + "</td>");
            pw.print("<td>" + item.qty + "</td>");
            pw.print("<td><input type='checkbox' value='" + item.product.id + "' name='removeFromCart'></td>");
            pw.print("</tr>");
        });

        pw.print("</table>");
        pw.print("<br /><input type='submit' value='Remove From Cart'>");
        pw.print("<br/ ><a href='ProductsPage'>View Products</a>");
        pw.print("</form>");
    }
}