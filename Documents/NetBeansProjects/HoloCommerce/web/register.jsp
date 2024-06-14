<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jdbc.dbcon"%>
<%@ page import="logic.registerLogic"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="RSC/resource.css">
        <title>Register Page</title>
    </head>
    <body>
        <div class="container">
            <div class="rightimg">
                <img src="RSC/suisei.jpg" alt="imgsuisei">
            </div>  
            <div class="login">
                <form method="post" action="registerLogic">
                    <h1>Register</h1>
                    <hr>
                    <% String message = (String) request.getAttribute("message"); %>
                    <% if (message != null) { %>
                        <p><%= message %></p>
                    <% } %>
                    <br>
                    <label>Username</label>
                    <input type="text" placeholder="username" name="nama" required> 
                    <br>
                    <label>Password</label>
                    <input type="password" placeholder="password" name="pass" required> 
                    <br> 
                    <br>
                    <button class="loginbtn" type="submit" name="btnsimpan" value="simpan">Register</button>
                    <br>
                    <a href="index.jsp">Already have an account? Click here</a>
                    <br>
                </form>
            </div>
        </div>
    </body>
</html>