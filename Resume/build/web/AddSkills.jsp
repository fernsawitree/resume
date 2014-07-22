<%-- 
    Document   : AddSkills
    Created on : Mar 3, 2014, 10:46:46 PM
    Author     : Fern Sawitree
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skills</title>
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <h1>Skills</h1>
        <div class="form">
            <form action="SkillsController" method="post">
            <label for="skill">Skill Title: </label><input type="text" name="title" size="20" id="skilltitle" value="${fn:escapeXml(param.title)}" /><br />
            <label for="skill">Description: </label><input type="text" name="description" size="20" id="description"/><br />
            <label for="skill">Years: </label><input type="text" name="years" size="20" id="years"/><br />
            <label for="skill">Skill Level: </label><input type="text" name="level" size="20" id="level"/><br />
            <label></label><input type="submit" value="Save" name="submit" class="button" />
 
        </div>
            <%--on submit append another div 
               increment ID from 0 to list.length - 1 (will give us the same 
            if list.length > 0 then display everything in skills table 
            
            //
            
            
            --%>
            
            
            <div style="color:red">${message}</div>
           
        
            
             
        </form>
    </body>
</html>
