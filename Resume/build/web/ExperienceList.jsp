<%-- 
    Document   : SkillsList
    Created on : Jul 20, 2014, 11:07:59 AM
    Author     : korea_fern
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Experience List</title>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <h1>Experience List</h1>
        <div class="list">
            
        <c:forEach items="${experiences}" var="item">
    
                   <c:out value="Company Name: ${item.companyName}" /><br/>
                   <c:out value="Job Title: ${item.designation}" /><br/>
                   <c:out value="Start Date: ${item.startDate}" /><br/>
                   <c:out value="End Date: ${item.endDate}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
            
    <a href="AddExperience.jsp">Add more experience</a><br/>
    <a href="AddEducation.jsp">Continue</a>
        </div>
         <footer>
            <p>Copyright 2014 - Resume Pro</p>
            
        </footer>
    </body>
</html>
