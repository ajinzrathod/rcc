 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      2-MultiplePageTrack.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.Date.*;

public class MultiplePageTrack extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("Text/html");
        PrintWriter pw = res.getWriter();

        pw.println("TrackUser is called.....");

        HttpSession session = req.getSession();
        Integer count = (Integer) session.getAttribute("keeptrack");
        if (count == null) {
            count = 0;
        }
        String surl = (String) session.getAttribute("url");
        if (surl == null) {
            surl = " ";
        }
        pw.println("<br> Pages Visited : " + surl);
        pw.println("<br> Request Count : " + count);
    }
}