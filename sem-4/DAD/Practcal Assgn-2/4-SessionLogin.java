 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      4-SessionLogin.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class SessionLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isValidUser;
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserValidator userValidator = new UserValidator();
        userValidator.ValidateUser(username, password, pw, session);
        if (session.getAttribute("name") == null) {
            pw.println("Login Failed");
            pw.println("<a HREF=\"/SessionLogin\">Login Page</a>");
        } else {
            pw.println("<h2>User Name : " + session.getAttribute("name") + "</h2>");
            pw.println("<h2>User Address : " + session.getAttribute("address") + "</h2>");
            pw.println("<h2>Date : " + session.getAttribute("date") + "</h2>");
            pw.println("<h2>Time : " + session.getAttribute("time") + "</h2>");
            pw.print("<a href=\"DestroySession\">Logout</a>");
        }
        pw.close();
    }
}