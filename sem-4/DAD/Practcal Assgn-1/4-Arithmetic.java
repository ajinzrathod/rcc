 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      4-Arithmetic.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class Arithmetic extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));

        pw.println("<html><head><title>Arithmetic Operations</title></head><body>"
                + " <table border=\"1\"> <tr> <th> Operation </th><th> Output </th>"
                + "</tr> <tr> <td> Addition </td> <td>" + (num1 + num2) + "</td> </tr>"
                + "</tr> <tr> <td> Subtraction </td> <td>" + (num1 - num2) + "</td> </tr>"
                + "</tr> <tr> <td> Multiplication </td> <td>" + (num1 * num2) + "</td> </tr>");
        try {
            pw.println("</tr> <tr> <td> Division </td> <td>" + (num1 / num2) + "</td> </tr>");
        } catch (ArithmeticException e) {
            pw.println("</tr> <tr> <td> Division Error </td> <td> Division with <b>zero</b> not possible. </td> </tr>");
        }
        pw.println(" </table> </body> </html>");

        pw.close();
    }
}
