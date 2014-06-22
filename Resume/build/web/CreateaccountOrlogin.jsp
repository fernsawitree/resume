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
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <header>
            <img src="images/resumepro.png" alt="Resume Pro Logo" />
        </header>
        <section class="main">
            <div class="left">
                <form action="CreateAccountLoginController" name="createaccount" method="post">
            <label for="fname">Fist Name: </label><input type="text" name="fname" id="ifirst_name" size="20" />
            <label for="lname">Last Name: </label><input type="text" name="lname" id="ilast_name" size="20"/>
            <label for="address1">Address 1: </label><input type="text" name="address1" id="iaddress1" size="20" />
            <label for="address2">Address 2: </label><input type="text" name="address2" id="iaddress2" size="20" />
            <label for="city">City: </label><input type="text" name="city" id="icity" size="15"/>
            <label for="state">State: </label><input type="text" name="state" id="istate" size="20" />
            <label for="zipcode">Zip Code: </lable><input type="text" name="zipcode" id="izipcode" size="10"/>
            <label for="email">Email: </label><input type="text" name="email" id="iemail" size="20" />
            <label for="username">Username: </label><input type="text" name="username" id="iuser_name" size="15" />
            <label for="password">Password: </label><input type="password" name="password" id="ipassword" size="20" />
            <input type="submit" value="Create Account" class="button" />
        </form>
            </div>
            <div class="right">
           <form action="LoginCheck.jsp" method="post" name="login">
            Username:<input type="text" name="username" id="iuser_name">
            Password: <input type="password" name="password" id="ipassword">
            <input type="submit" value="Login" class="button">
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
