 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      4-DestroySession.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;

public class DestroySession extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.invalidate();

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        out.println("<HEAD><TITLE> " + "Logout" + "</TITLE></HEAD><BODY>");
        out.println("<P>[<A HREF=\"/SessionLogin\">Login Page</A>]");
        out.println("<h2> Logged Out Successfully</h2>");
        out.close();
    }
}
