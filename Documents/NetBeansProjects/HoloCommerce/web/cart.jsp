<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="cart" scope="session" class="logic.ShoppingCart" />

<%
    String action = request.getParameter("action");
    String itemId = request.getParameter("itemId");

    if (action != null && itemId != null) {
        if (action.equals("increase")) {
            cart.addItem(itemId, "", 0, 1);
        } else if (action.equals("decrease")) {
            cart.removeItem(itemId);
        }
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Shopping Cart Content</title>
    <link rel="stylesheet" href="RSC/cartStyle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>
    <nav class="navbar">
            <ul>
              <li class="ic">
               HoloStore
              </li>
              <li class="end-items">
                <a href="product.jsp">Product / Merchandise</a>
              </li>
              <li class="end-items">
                <a href="about.jsp">About us</a>
              </li>
              <li class="end-items">
                <a href="contact.jsp">Contact</a>
              </li>
            </ul>
        </nav>
    <center>
        <table>
            <caption>My Shopping Cart</caption>
            <tr>
                <th>Description</th>
                <th>Total Quantity</th>
                <th>Price</th>
                <th>Total Price</th>
                <th>Actions</th>
            </tr>
            <%
                Enumeration e = cart.getEnumeration();
                String[] tmpItem;
                float totalPrice = 0; // Initialize total price variable
                int totalQuantity = 0;
                while (e.hasMoreElements()) {
                    tmpItem = (String[]) e.nextElement();
                    float itemPrice = Float.parseFloat(tmpItem[2]); // Get item price
                    int itemQuantity = Integer.parseInt(tmpItem[3]); // Get item quantity
                    float itemTotalPrice = itemPrice * itemQuantity; // Calculate total price for this item
                    totalPrice += itemTotalPrice; // Add item total price to overall total price
                    totalQuantity += itemQuantity;
            %>
            <tr>
                <td><%= tmpItem[1] %></td>
                <td><%= itemQuantity %></td> <!-- Display total quantity for this item -->
                <td>$<%= tmpItem[2] %></td>
                <td>$<%= String.format("%.2f", itemTotalPrice) %></td> <!-- Display total price for this item -->
                <td>
                    <form action="cart.jsp" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="increase">
                        <input type="hidden" name="itemId" value="<%= tmpItem[0] %>">
                        <button type="submit">+</button>
                    </form>
                    <form action="cart.jsp" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="decrease">
                        <input type="hidden" name="itemId" value="<%= tmpItem[0] %>">
                        <button type="submit">-</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="2" align="right">Total Quantity:</td>
                <td colspan="2" align="center"><%= totalQuantity %></td>
            </tr>
            <tr>
                <td colspan="2" align="right">Total Price:</td>
                <td colspan="2" align="center">$<%= String.format("%.2f", totalPrice) %></td>
            </tr>
        </table>
            <div class="btn">
                <button onclick="location.href='product.jsp'" style="margin-right: 2%;">Back to product</button>
            <button onclick="location.href='payment.jsp'">Next Payment</button>
            </div>
    </center>
</body>
</html>
