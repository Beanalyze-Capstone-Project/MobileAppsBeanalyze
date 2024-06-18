package produk;

import jdbc.dbcon; // Assuming this is your database connection class
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.*;

@WebServlet("/produkCreate")
@MultipartConfig
public class produkCreate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Data Collection and Validation
        String nmproduk = request.getParameter("nmproduk");
        String kategori = request.getParameter("kategori");
        int harga; 
        try {
            harga = Integer.parseInt(request.getParameter("harga"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Invalid price format.");
            response.sendRedirect(request.getContextPath() + "/adminHome.jsp");
            return; // Exit early if price is invalid
        }

        Part filePart = request.getPart("gambarproduk");
        String gambarProdukNama = null;
        InputStream gambarProdukStream = null;

        // Image Handling (with null checks)
        if (filePart != null && filePart.getSize() > 0) {
            gambarProdukNama = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            gambarProdukStream = filePart.getInputStream();
        }

        // Database Interaction (try-with-resources for automatic closing)
        try (Connection conn = new dbcon().bukaKoneksi();  
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO product (nmproduk, gambarproduk, kategori, harga) VALUES (?, ?, ?, ?)")) {

            // Parameter Setting (null-safe image handling)
            pstmt.setString(1, nmproduk);
            if (gambarProdukStream != null) { // Only save image path if it exists
                String uploadPath = getServletContext().getRealPath("/") + "uploads/";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // Create uploads directory if it doesn't exist
                }
                String filePath = uploadPath + gambarProdukNama;
                Files.copy(gambarProdukStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                pstmt.setString(2, "uploads/" + gambarProdukNama); 
            } else {
                pstmt.setNull(2, Types.VARCHAR); // Explicitly set NULL for no image
            }
            pstmt.setString(3, kategori);
            pstmt.setInt(4, harga);
            pstmt.executeUpdate();

            request.getSession().setAttribute("message", "Data berhasil disimpan");

        } catch (SQLException e) {
            request.getSession().setAttribute("message", "Error: " + e.getMessage());
        } 

        response.sendRedirect(request.getContextPath() + "/adminHome.jsp"); 
    }
}
