<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="RSC/resource.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="rightimg">
                <img src="RSC/suisei.jpg" alt="imgsuisei">
            </div>  
            <div class="login">
                <form action="validate" method="post">
                    <h1>Login</h1> <hr> <br>
                    <label>Username</label>
                    <input type="text" placeholder="username" name="user"> <br>
                    <label>Password</label>
                    <input type="password" placeholder="" name="pass"> <br>
                    <% String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) { %>
                        <div class="alert alert-danger"><%= errorMessage %></div> <%-- Display alert --%>
                    <% } %>
                    <a href="register.jsp">Don't have an account? Click Here</a> <br> <br>
                    <button class="loginbtn" type="submit">Login</button>
                </form>
            </div>
        </div>
    </body>
</html>
