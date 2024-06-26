<%@ page import="java.util.*" %>
<jsp:useBean id="cart" scope="session" class="logic.ShoppingCart" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="receipt-container">
            <h2>Payment Receipt</h2>
            <h3>Nama: <%=(session.getAttribute("user") != null) ? session.getAttribute("user") : "" %></h3>
            <%
                Date date = new Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String currentDate = sdf.format(date);
            %>
            <p>Tanggal: <%= currentDate %></p>
            <table>
                <tr>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
                <%
                    Enumeration e = cart.getEnumeration();
                    String[] tmpItem;
                    int totalQuantity = 0;
                    float totalPrice = 0.0f;
                    while (e.hasMoreElements()) {
                        tmpItem = (String[]) e.nextElement();
                        int quantity = Integer.parseInt(tmpItem[3]);
                        float price = Float.parseFloat(tmpItem[2]);
                        totalQuantity += quantity;
                        totalPrice += price * quantity;
                %>
                <tr>
                    <td><%= tmpItem[1] %></td>
                    <td align="center">Rp <%= String.format("%.2f", price) %></td>
                    <td align="center"><%= quantity %></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td class="total">Total</td>
                    <td class="total" align="center">Rp <%= String.format("%.2f", totalPrice) %></td>
                    <td class="total" align="center"><%= totalQuantity %></td>
                </tr>
            </table>
            <div class="instructions">
                <p>Silahkan transfer ke rekening berikut:</p>
                <p><strong>Bank Mandiri</strong></p>
                <p><strong>Nomor Rekening: 123456789</strong></p>
                <p><strong>Atas Nama: PT apacoba</strong></p>
            </div>
            <div class="buttons">
                <a href="homepage.jsp">Back to homepage</a>
                <form action="CompletePayment.jsp" method="post">
                    <input type="submit" value="Complete Payment">
                </form>
            </div>
        </div>
    </body>
</html>
