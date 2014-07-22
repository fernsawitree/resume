<%-- 
    Document   : EnterInfo
    Created on : Mar 3, 2014, 11:15:21 PM
    Author     : Fern Sawitree
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
        <title>Enter Information</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <form action="AddressController" method="post">
            
        </form>
        <form action="ExperienceController" method="post">
            <%--Experience Section --%>
            <label for="companyname">Company Name: </label><input type="text" name="companyname" id="companyname" /><br />
            <label for="designation">Designation: </label><input type="text" name="designation" id="designation" /><br />
            <label for="startdate">Start Date: </label><input type="text" name="start_date" id="startdate" /><br />
            <label for="enddate">End Date: </label><input type="text" name="end_date" id="enddate" /><br />
            <label for="description">Description: </label><input type="text" name="description" id="description" /><br />
            <input type="submit" name="submit" value="Save" /><br />
            <input type="submit" name="addexperience" value="Add Experience" /><br />
        </form>
             <div style="color:red">${message}</div>  
        <form action="EducationController" method="post">
            <%--Education Section --%>
            <label for="institutionname">Institution Name: </label><input type="text" name="institutename" size="20"  id="institutename"/><br />
            <label for="degreename">Degree: </label><input type="text" name="degreename" size="50" id="degreename"/><br />
            <label for="startdate">Start Date: </label><input type="text" name="startdate" size="10" id="startdate"/><br />
            <label for="enddate">End Date: </label><input type="text" name="enddate" size="10" id="enddate"/><br />
            <label for="description">Description: </label><input type="text" name="description" size="50" id="description"/><br />
            <input type="submit" name="submit" value="Save" /><br />
            <input type="submit" name="addeducation" value="Add Education" /><br />
        </form>
        
        <%--Skills Section --%>
        <form action="SkillsController" method="post">
            <label for="skill">Skill Title: </label><input type="text" name="skilltitle" size="20" id="skilltitle"/><br />
            <label for="skill">Description: </label><input type="text" name="description" size="20" id="description"/><br />
            <label for="skill">Years: </label><input type="text" name="years" size="20" id="years"/><br />
            <label for="skill">Skill Level: </label><input type="text" name="level" size="20" id="level"/><br />
            <input type="submit" name="submit" value="Save" /><br />
            <input type="submit" name="addskills" value="Add skills" /><br />
        </form> 
        <a href="ChooseTemplate.jsp">Continue</a>
    </body>
</html>
