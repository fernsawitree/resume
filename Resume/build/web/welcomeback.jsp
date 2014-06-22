<%-- 
    Document   : welcomeback
    Created on : Mar 13, 2014, 10:19:42 PM
    Author     : Fern Sawitree Euamethiyangkool
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Back</title>
    </head>
    <body>
        <a href ="logout.jsp">Logout</a>
        <%
            String a = session.getAttribute("email").toString();
            out.println("Welcome Back "+a);
            %>
    </body>
</html>
