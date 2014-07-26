<%-- 
    Document   : ChooseTemplate
    Created on : May 16, 2014, 1:26:23 PM
    Author     : korea_fern
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Template</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <div id="template">
        <form action="ChooseTemplate" method="post">
            <div id="template1">
        <img src="images/resume1.jpg" /><br/>    
        <label>Template 1</label><input type="radio" name="Template" value="template1" />
            </div>
            <div id="template2">
        <img src="images/resume1.jpg" /><br />
        <label>Template 2</label><input type="radio" name="Template" value="template2" /><br />
            </div>
        <input type="submit" value="Choose template" />
        </form>
        </div>
         <footer>
            <p>Copyright 2014 - Resume Pro</p>
            
        </footer>
    </body>
</html>
