<%-- 
    Document   : template2
    Created on : Jul 13, 2014, 11:30:01 AM
    Author     : korea_fern
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template 2</title>
        <link rel="stylesheet" type="text/css" href="style.css">
         <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body class="template2">
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
         <div class="address-template2">
        <h1>${user.firstname} ${user.lastname}</h1>
        ${user.address}, ${user.city}, ${user.state}, ${user.zipcode} <br/>
        ${user.email} | ${user.phoneNumber}
        <hr/>
        </div>
        <div id="skills">
            <h1>Skills</h1>
            <div class="list">
             <c:forEach items="${skills}" var="item">
            <c:out value="Skill Title: ${item.title}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <c:out value="Years: ${item.years}" /><br/>
                   <c:out value="Skill Level: ${item.level}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
            </div>
        </div>
                   
                       <h1>Experience</h1>
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
                   </div>
                   
                       <h1>Education</h1>
                       <div class="list">
                       <c:forEach items="${educations}" var="item">
            <c:out value="Institution Name: ${item.instituteName}" /><br/>
                   <c:out value="Degree Name: ${item.degreeName}" /><br/>
                   <c:out value="Start Date: ${item.startdate}" /><br/>
                   <c:out value="End Date: ${item.enddate}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
                   </div>
         <a style="margin-left:50%;margin-top:40px;font-size: 20px;font-weight:bold;" href="ChooseTemplate.jsp">Back</a>
         <footer>
            <p>Copyright 2014 - Resume Pro</p>
            
        </footer>
    </body>
</html>
