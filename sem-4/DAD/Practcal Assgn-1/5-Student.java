 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      5-Student.java 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class Student extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        long number = 0;
        ArrayList arrayList = new ArrayList();

        String name = request.getParameter("name");
        if (name == null || name.equals(""))
            arrayList.add("Invalid Student Name.");

        String currentClass = request.getParameter("class");
        if (currentClass == null || currentClass.equals(""))
            arrayList.add("Invalid Student Class.");

        String gender = request.getParameter("gender");
        if (gender == null || gender.equals(""))
            arrayList.add("Invalid Student Gender.");

        try {
            number = Long.parseLong(request.getParameter("number"));
        } catch (NumberFormatException numberFormatException) {
            arrayList.add("Invalid Mobile Number.");
        }

        if (arrayList.size() > 0) {
            pw.println(arrayList);
        } else {
            if (StudentDataSave.SaveStudent(name, currentClass, gender, number, pw))
                pw.println("Student Data Inserted.....");
        }

        pw.close();
    }
}
