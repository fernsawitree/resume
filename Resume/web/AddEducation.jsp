<%-- 
    Document   : AddEducation
    Created on : Mar 3, 2014, 10:39:19 PM
    Author     : Fern
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Education</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <h1>Education</h1>
        <div class="form">
        <form action="EducationController" method="post" class="form">
            <label for="instituteName">Institution Name: </label><input type="text" name="instituteName" size="20" /><br/>
            <label for="degreeName">Degree: </label><input type="text" name="degreeName" size="20" /><br/>
            <label for="startdate">Start Date: </label><input type="text" name="startdate" size="20" /><br/>
            <label for="enddate">End Date: </label><input type="text" name="enddate" size="20" /><br/>
            <label for="description">Description: </label><input type="text" name="description" size="20" /><br/>
            <input type="submit" value="save" name="submit" class="button"/>
         
            <div style="color:red">${message}</div>
            
        </form>
        </div>
             <footer>
            <p>Copyright 2014 - Resume Pro</p>
            
        </footer>
    </body>
</html>


