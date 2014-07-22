<%-- 
    Document   : template1
    Created on : Jul 13, 2014, 11:29:54 AM
    Author     : korea_fern
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template 1</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        ${user.firstname} ${user.lastname}
        
        <a href="ChooseTemplate.jsp">Back</a>
        <a href="summary.jsp">Next</a>
        
    </body>
</html>
