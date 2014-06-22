<%-- 
    Document   : LoginCheck
    Created on : Mar 13, 2014, 10:05:51 PM
    Author     : Fern Sawitree Euamehtiyangkool
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Login</title>
    </head>
    <body>
        <%
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if ((email.equals("email") && password.equals("password")))
        {
            session.setAttribute("email", email);
            response.sendRedirect("EnterInfo.jsp");
        }
        else
            response.sendRedirect("Error.jsp");
        
        %>
        
        
    </body>
</html>
