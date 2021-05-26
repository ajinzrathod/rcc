 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      3-FilterCh.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FilterCh extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            pw.println(e);
        }
    }
}