 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      1-LastVisit.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LastVisit extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Date date = new Date();

        String dateString = String.valueOf(date.getTime());

        Cookie cookie = new Cookie("Pradip", dateString);
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("Pradip")) {
                    long lastTime = Long.parseLong(cookies[i].getValue());
                    pw.println("Your Last Visit :  " + new Date(lastTime));
                    pw.println("<br>Duration of time since Last Visit : " + ((date.getTime() - lastTime) / 1000)
                            + " Seconds");
                }
            }
        }

    }
}