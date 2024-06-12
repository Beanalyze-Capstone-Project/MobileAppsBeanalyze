package logic;

import jdbc.dbcon;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "validate", urlPatterns = {"/validate"})
public class validate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("user");
            String password = request.getParameter("pass");
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                dbcon konek = new dbcon();
                conn = konek.bukaKoneksi();
                String sql = "SELECT * FROM users WHERE nama = ? AND pass = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // User found, set session and redirect to homepage
                    HttpSession session = request.getSession();
                    session.setAttribute("user", name);

                    RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
                    rd.forward(request, response);
                } else {
                    // User not found, send error message
                    request.setAttribute("errorMessage", "<font color='red'><b>Invalid username or password.</b></font><br><br>");
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "<font color='red'><b>Error: " + e.getMessage() + "</b></font><br><br>");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.include(request, response);
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            out.close();
        }
    }
}