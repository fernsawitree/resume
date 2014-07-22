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
        <title>Skills List</title>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <h1>Skills List</h1>
        <div class="list">
            
        <c:forEach items="${skills}" var="item">
    
            <c:out value="Skill Title: ${item.title}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <c:out value="Years: ${item.years}" /><br/>
                   <c:out value="Skill Level: ${item.level}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
            
    <a href="AddSkills.jsp">Add more skills</a><br/>
    <a href="AddExperience.jsp">Continue</a>
        </div>
    </body>
</html>
