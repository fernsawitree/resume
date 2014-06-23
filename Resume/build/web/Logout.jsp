<%-- 
    Document   : Logout
    Created on : Mar 14, 2014, 11:18:04 AM
    Author     : Fern Sawitree Euamethiyangkool
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Out</title>
    </head>
    <body>
        <%
            session.removeAttribute("email");
            session.removeAttribute("password");
            session.invalidate();
            %>
        
        
        <h1>You have successfully logged out.</h1>
    </body>
</html>
