package logic;

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

            if (password.equals("kenny") && name.equals("kenny")) {
                // Menyimpan nama ke dalam sesi
                HttpSession session = request.getSession();
                session.setAttribute("user", name);

                RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "<font color='red'><b>Invalid username or password.</b></font><br><br>");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.include(request, response);
            }
        } finally {
            out.close();
        }
    }
}
