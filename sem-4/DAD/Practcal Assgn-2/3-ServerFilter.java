 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      3-ServerFilter.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.time.LocalTime;

public class ServerFilter implements Filter {
    ServletContext context;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        PrintWriter pw = res.getWriter();

        pw.println("<p>Time OF Request : " + LocalTime.now());

        long startTime = System.currentTimeMillis();
        chain.doFilter(req, res);

        long endTime = System.currentTimeMillis();
        pw.println("</p> <p>Time Of Response : " + LocalTime.now());
        pw.println("</p> <p>Time take to process the request : " + (endTime - startTime) + " miliseconds");
        pw.println("</p> <p>URL of Resource Requested : " + request.getRequestURL());
        pw.println("</p> <p>IP Address Of Visitor : " + request.getRemoteAddr() + "</p>");
    }

    public void init(FilterConfig config) {
        context = config.getServletContext();
    }

    public void destroy() {
    }
}