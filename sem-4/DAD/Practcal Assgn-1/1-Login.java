 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      1-Login.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean isValidUser;
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserValidator userValidator = new UserValidator();
        isValidUser = userValidator.ValidateUser(username, password, pw);
        if (!isValidUser)
            pw.println("Login Failed");
        else
            pw.println("Login Sucess");

        pw.close();
    }
}