 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      3-.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        System.out.println("\n***************************************\n");
        System.out.println("HelloWorld servlet init method has been called.");
        System.out.println("\n***************************************\n");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Header Info, Method Type, Query String";
        String docType = "<!DOCTYPE html public \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
        out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body>\n"
                + "<h1 align=\"center\">" + title + "</h1>\n" + "<table width=\"100%\" border=1 align=\"center\">\n"
                + "<tr>\n" + "<th>Name</th><th>Value(s)</th>\n"
                + "</tr> \n<tr>\n<th colspan=\"2\">Header Info</th>\n</tr>");

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            out.print("<tr><td>" + headerName + "</td>\n");
            String headerValue = request.getHeader(headerName);
            out.println("<td>" + headerValue + "</td></tr>\n");
        }
        out.println("<tr>\n<th colspan=\"2\">Method Type</th>\n</tr>" + "<tr>\n<td>Method Type</td>\n<td>"
                + request.getMethod() + "</td>\n</tr>" + "<tr>\n<th colspan=\"2\">Query String</th>\n</tr>"
                + "<tr>\n<td>Query String</td>\n<td>" + request.getQueryString()
                + "</td>\n</tr>\n</table>\n</body>\n</html>");
    }
}
