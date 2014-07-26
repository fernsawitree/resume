<%-- 
    Document   : index
    Created on : Mar 3, 2014, 5:01:46 PM
    Author     : Fern Sawitree Euamethiyangkool
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:redirect url="CreateAccount"/>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Resume Pro</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <section class="main">
            <h1>Need a resume? Get started with Resume Pro!</h1>
            <div class="create_account">
                <h1>New Users?</h1>
                <a href="CreateAccount.jsp"><h1>Create Account Today</h1></a>
            </div>
            <div class="login">
                <h1>Already have an account?</h1>
                <a href="login.jsp"><h1>Login</h1></a>
            </div>
           
            <div class="clear"></div>
        </section>
        <footer>
            <img src="images/resume1.jpg" alt="resume1" />
            <img src="images/resume1.jpg" alt="resume1" />
             
            <p>Copyright 2014 - Resume Pro</p>
            
        
        </footer>
    </body>
</html>

