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
        <title>Add Education</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
    
        <form action="EducationController" class="form">
            <label for="institutionname">Institution Name: </label><input type="text" name="institutionname" size="20" />
            <label for="degreename">Degree: </label><input type="text" name="degreename" size="50" />
            <label for="startdate">Start Date: </label><input type="text" name="startdate" size="10" />
            <label for="enddate">End Date: </label><input type="text" name="enddate" size="10" />
            <label for="description">Description: </label><input type="text" name="description" size="50" />
            <input type="submit" value="Add" />
            
            
        </form>
    </body>
</html>


