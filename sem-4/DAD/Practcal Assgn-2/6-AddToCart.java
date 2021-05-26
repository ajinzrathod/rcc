 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-AddToCart.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        if (session == null) {
            pw.println("-- creating new session in the servlet --");
            session = request.getSession(true);
            pw.println("-- session created in the servlet --");
        }

        String[] selectedProductIds = request.getParameterValues("addToCart");
        Database.addToCart(selectedProductIds, pw);

        for (int i = 0; i < selectedProductIds.length; i++) {
            Object attr = session.getAttribute(selectedProductIds[i]);
            if (attr != null)
                session.setAttribute(selectedProductIds[i], Integer.parseInt(attr.toString()) + 1);
            else
                session.setAttribute(selectedProductIds[i], 1);
        }

        double total = Database.getTotalPrice(session, pw);
        System.out.println("Total Price: " + total);

        pw.println("<a href='ShopCart'>View Cart</a>");
        pw.println("<a href='ProductsPage'>View Products</a>");

        pw.close();
    }
}