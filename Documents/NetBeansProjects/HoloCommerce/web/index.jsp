<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="RSC/resource.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
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
