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
    </head>
    <body>
        <form action="EnterInfoController" method="post">
            <%--Experience Section --%>
            <label for="companyname">Company Name: </label><input type="text" name="companyname" id="icompanyname" />
            <label for="designation">Designation: </label><input type="text" name="designation" id="idesignation" />
            <label for="startdate">Start Date: </label><input type="text" name="start_date" id="istart_date" />
            <label for="enddate">End Date: </label><input type="text" name="end_date" id="iend_date" />
            <label for="description">Description: </label><input type="text" name="description" id="iex_description" />
            <p><a href="AddExperience.jsp">Add Experience</a></p>
            <%--Education Section --%>
            <label for="institutionname">Institution Name: </label><input type="text" name="institute_name" size="20"  id="iinstitute_name"/>
            <label for="degreename">Degree: </label><input type="text" name="degreename" size="50" id="idegree_name"/>
            <label for="startdate">Start Date: </label><input type="text" name="startdate" size="10" id="istartdate"/>
            <label for="enddate">End Date: </label><input type="text" name="enddate" size="10" id="ienddate"/>
            <label for="description">Description: </label><input type="text" name="description" size="50" id="ied_description"/>
            <p><a href="AddEducation.jsp">Add Education</a></p>
            <%--Skills Section --%>
            <label for="skill">Skills: </label><input type="text" name="skill" size="20" id="skills_info"/>
            <a href="AddSkills.jsp">Add Skills</a>
            <p><input type="submit" value="Save and continue" name="submit" /></p>
            
            
            
            
        </form> 
    </body>
</html>
