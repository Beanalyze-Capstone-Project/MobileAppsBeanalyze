package logic;

import jdbc.dbcon;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "registerLogic", urlPatterns = {"/registerLogic"})
public class registerLogic extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String message = null;
        try {
            String nama = request.getParameter("nama");
            String pass = request.getParameter("pass");  
            String dapat = request.getParameter("btnsimpan");
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
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            out.close();
        }
    }
}
