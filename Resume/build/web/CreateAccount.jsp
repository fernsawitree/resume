<%-- 
    Document   : CreateAccount
    Created on : Mar 3, 2014, 9:53:19 PM
    Author     : Fern Sawitree
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" alt="Resume Pro Logo" />
        </header>
        <section class="main">
            <div class="left">
                <form action="CreateAccount" name="createaccount" method="post">
            <label for="fname">Fist Name: </label><input type="text" name="fname" id="fname" size="20" />
            <label for="lname">Last Name: </label><input type="text" name="lname" id="lname" size="20"/>
            <label for="email">Email: </label><input type="text" name="email" id="email" size="20" />
            <label for="username">Username: </label><input type="text" name="username" id="iuser_name" size="15" />
            <label for="password">Password: </label><input type="password" name="password" id="ipassword" size="20" />
            <input type="submit" value="Create Account" class="button" />
        </form>
            </div>
           
            <div class="clear"></div>
        </section>
        <footer>
            <img src="images/resume1.jpg" alt="resume1" />
            <img src="images/resume1.jpg" alt="resume1" />
            
        </footer>
    </body>
</html>
