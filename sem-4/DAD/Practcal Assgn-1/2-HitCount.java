 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      2-HitCount.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class HitCount extends HttpServlet {
    private int hitCount = 0;

    public void init() {
        hitCount = 0;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        hitCount++;
        PrintWriter pw = response.getWriter();
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        pw.println(docType + "<html>\n" + "<head><title>HitCount</title></head>\n" + "<body bgcolor = \"#f0f0f0\">\n"
                + "<p>" + hitCount + "</p>" + "</body> </html>");
    }
}