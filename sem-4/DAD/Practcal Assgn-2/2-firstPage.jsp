 /* ===========================================================
 * 
 * Roll No: 30
 * 
 * File:      2-firstPage.jsp 
 * Copyright: by Ajinkya Rathod(ajinzrathod)
 * 
 * ========================================================== */  

 <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>First Page</title>
  </head>
  <body>
    <h2>First page</h2>
    <% Integer count=(Integer)session.getAttribute("keeptrack"); if(count==null)
    { count=0; } session.setAttribute("keeptrack",++count); %>
    <br />
    Tracking = <%= count %> <% String rurl=request.getRequestURI(); String
    surl=(String)session.getAttribute("url"); session.setAttribute("url", rurl+
    " " +surl); %>
  </body>
</html>
