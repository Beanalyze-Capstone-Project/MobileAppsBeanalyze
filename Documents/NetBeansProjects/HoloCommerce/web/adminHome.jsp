<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jdbc.dbcon"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Daftar Produk</title>
    <style>
        .table-barang { width: 100%; border-collapse: collapse; }
        .table-barang th, .table-barang td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        .addbtn { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h3>Daftar Produk</h3>

    <table border="1" class="table-barang">
        <tr>
            <th>Nama Produk</th>
            <th>Gambar</th>
            <th>Kategori</th>
            <th>Harga</th>
        </tr>

        <% 
            try (Connection conn = new dbcon().bukaKoneksi();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM product")) {
                
                while (rs.next()) {
                    String imagePath = rs.getString("gambarproduk");
        %>

        <tr>
            <td><%= rs.getString("nmproduk") %></td>
            <td>
                <% if (imagePath != null && !imagePath.isEmpty()) { %>
                    <img src="<%= request.getContextPath() %>/<%= imagePath %>" alt="Gambar Produk" width="100">
                <% } else { %>
                    <p>Tidak ada gambar</p>
                <% } %>
            </td>
            <td><%= rs.getString("kategori") %></td>
            <td><%= rs.getString("harga") %></td>
        </tr>

        <% 
                }
            } catch (SQLException e) {
                out.println("Error: " + e.getMessage());
            } 
        %>

    </table>

    <button class="addbtn" onclick="window.location.href = 'addproduk.jsp';">Tambah Produk</button>
</body>
</html>