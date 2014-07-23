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
        <div id="address">
        <h1>${user.firstname} ${user.lastname}</h1>
        ${user.address}, ${user.city}, ${user.state}, ${user.zipcode} <br/>
        ${user.email}, ${user.phonenumber}
        <hr/>
        </div>
        <div id="skills">
             <c:forEach items="${skills}" var="item">
    
            <c:out value="Skill Title: ${item.title}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <c:out value="Years: ${item.years}" /><br/>
                   <c:out value="Skill Level: ${item.level}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
        </div>
                   <div id="experience">
                       <c:forEach items="${experiences}" var="item">
    
            <c:out value="Company Name: ${item.companyname}" /><br/>
                   <c:out value="Job Title: ${item.designation}" /><br/>
                   <c:out value="Start Date: ${item.startdate}" /><br/>
                   <c:out value="End Date: ${item.enddate}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
                   </div>
                   <div id="education">
                       <c:forEach items="${educations}" var="item">
            <c:out value="Institution Name: ${item.institutionname}" /><br/>
                   <c:out value="Degree Name: ${item.degreename}" /><br/>
                   <c:out value="Start Date: ${item.startdate}" /><br/>
                   <c:out value="End Date: ${item.enddate}" /><br/>
                   <c:out value="Description: ${item.description}" /><br/>
                   <a href="" >Edit</a><a href="">Delete</a>
                   <hr/>
            </c:forEach>
                   </div>
        <a href="ChooseTemplate.jsp">Back</a>
        <a href="">Edit</a>
        
    </body>
</html>
