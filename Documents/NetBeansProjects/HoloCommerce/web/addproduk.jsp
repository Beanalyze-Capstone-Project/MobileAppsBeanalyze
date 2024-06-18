<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jdbc.dbcon"%>
<%@ page import="produk.produkCreate"%>
<%@ page import="java.sql.*"%>
<html>
<head>
    <title>Tambah Produk Baru</title>
    <style>
        /* Tambahkan gaya CSS sesuai kebutuhan */
        body { font-family: sans-serif; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; margin-bottom: 10px; box-sizing: border-box; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; cursor: pointer; }
    </style>
</head>
<body>

    <h2>Tambah Produk Baru</h2>

    <<form action="produkCreate" method="post" enctype="multipart/form-data">>
        <label for="nmproduk">Nama Produk:</label>
        <input type="text" id="nmproduk" name="nmproduk" required><br><br>

        <label for="gambarproduk">Gambar Produk:</label>
        <input type="file" id="gambarproduk" name="gambarproduk" accept="image/*" required><br><br>

        <label for="kategori">Kategori:</label>
        <input type="text" id="kategori" name="kategori" required><br><br>

        <label for="harga">Harga:</label>
        <input type="number" id="harga" name="harga" required><br><br>
        <button type="submit" name="svproduk" value="simpanproduk">simpan</button>
    </form>

    <% 
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<p>" + message + "</p>");
        }
    %>
</body>
</html>