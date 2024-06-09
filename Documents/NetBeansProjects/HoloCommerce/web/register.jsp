<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jdbc.dbcon"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="RSC/resource.css">
        <title>Register Page</title>
    </head>
    <body>
        <!-- Register Logic -->
        <%
        String nama = request.getParameter("nama");
        String pass = request.getParameter("pass");  
        String dapat = request.getParameter("btnsimpan");
        String message = null;

        if (dapat != null && dapat.equals("simpan")) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                dbcon konek = new dbcon();
                conn = konek.bukaKoneksi();
                String sql = "INSERT INTO users (nama, pass) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nama);
                pstmt.setString(2, pass);
                pstmt.executeUpdate();
                message = "berhasil disimpan";
            } catch (Exception e) {
                message = "Error: " + e.getMessage();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        request.setAttribute("message", message);
        %>
        <div class="container">
            <div class="rightimg">
                <img src="RSC/suisei.jpg" alt="imgsuisei">
            </div>  
            <div class="login">
                <form method="post" action="register.jsp">
                    <h1>Register</h1>
                    <hr>
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
