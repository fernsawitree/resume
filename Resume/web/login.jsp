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
        <title>Welcome to Resume Pro</title>
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
         <header>
            <img src="images/resumepro.png" height="150" alt="Resume Pro Logo" />
        </header>
        <section class="main">
          
            <div id="login">
           <form action="LoginController" method="post" name="login">
            Username:<input type="text" name="username" id="username"><br />
            Password: <input type="password" name="password" id="password"><br />
            <input type="submit" value="Login" class="button">
        </form>
                <div style="color:red">${message}</div> 
            </div>
            <div class="clear"></div>
        </section>
        <footer>
            
        </footer>
    </body>
</html>
