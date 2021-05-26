 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      6-RemoveFromCart.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

public class RemoveFromCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        response.setContentType("text/html");

        String[] selectedProductIds = request.getParameterValues("removeFromCart");
        if (selectedProductIds.length < 1)
            return;

        HttpSession session = request.getSession(false);

        if (session == null) {
            pw.println("-- creating new session in the servlet --");
            session = request.getSession(true);
            pw.println("-- session created in the servlet --");
        }

        Database.removeFromCart(selectedProductIds, pw);

        for (int i = 0; i < selectedProductIds.length; i++) {
            Object attr = session.getAttribute(selectedProductIds[i]);
            if (attr != null)
                session.removeAttribute(selectedProductIds[i]);
        }

        double total = Database.getTotalPrice(session, pw);
        System.out.println("Total Price: " + total);

        pw.print("<a href='ShopCart'>View Cart</a>");
        pw.print("&nbsp;&nbsp;&nbsp;&nbsp;<a href='ProductsPage'>View Products</a>");

    }
}