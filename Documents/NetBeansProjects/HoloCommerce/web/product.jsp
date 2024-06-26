<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<jsp:useBean id="cart" scope="session" class="logic.ShoppingCart" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="RSC/productStyle.css">
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
              <li><a href="cart.jsp" style="color:#fff;">
                      Total Items in Cart: <%= (cart != null) ? cart.getNumOfItems() : 0 %>
                  </a></li>
            </ul>
        </nav>

        <div class="content" id="#product">
            <h1>Merchandise</h1>
            <div class="product-container">
                <h2>All Merch</h2>
            <%
                String id = request.getParameter("id");
                if (id != null) {
                    String desc = request.getParameter("desc");
                    Float price = null;
                    try {
                        price = Float.parseFloat(request.getParameter("price"));
                    } catch (NumberFormatException e) {
                    }
                    if (price != null) {
                        cart.addItem(id, desc, price, 1);
                    }
                }

                String deleteId = request.getParameter("deleteId");
                if (deleteId != null) {
                    cart.removeItem(deleteId);
                }
            %>
                <table>
                    <tbody>
                        <%
                            String[] descriptions = {
                                "HOLOLIVE Justice Acrylic Panel", "ReGLOSS's PLAYLIST 02 Acrylic Dioram Stans", "Canvas Art : Not too Sweet, Please! ", 
                                "Vestia Zeta Birthday Celebration", "Holoh3ro Card case - Vestia Zeta", "Hololive production Art Collection", 
                                "Hoshimachi Suisei 2nd Solo Live : Shout in Crisis", "Hoshimachi Suisei 2nd Album : Spectre", "Hoshimachi Suisei 1st Album : Still Still Stellar", 
                                "Hololive friends with u Kobo Kanaeru", "Hololive friends with u Vestia Zeta", "Hololive friends with u Kaela Kovalskia"
                            };

                            String[] prices = {
                                "IDR 400,000", "IDR 450,000", "IDR 350,000", 
                                "IDR 500,000", "IDR 300,000", "IDR 300,000", 
                                "IDR 2,500,000", "IDR 1,000,000", "IDR 4,750,000", 
                                "IDR 400,000", "IDR 400,000", "IDR 400,000"
                            };

                            String[] images = {
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_justice_debutbanner_240618_en_3_1718796657_700x.png?v=1718795797", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_regloss_2_banner_240527_en_3_1718009352_700x.png?v=1718008597", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_kaela_pemaloe_banner_240529_en_3_1717558730_700x.png?v=1717558419", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/t/23/assets/vestiazeta_2022_tanpin01_en_1101_1667827183_700x.png?v=1667312464", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_1028_holoid3rd_3d_tanpin1_en_1698219685_700x.png?v=1698218180", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/t/23/assets/expo2023_ec_zuroku_en_tanpin01_1678968662_700x.png?v=1678701179", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_sic_bd_banner_2023_en_ec_01_1695271004_700x.png?v=1695270881", 
                                "https://shop.hololivepro.com/cdn/shop/products/suisei_2ndAlbum_item01_jp_25b60223-8915-424f-9421-d2b5fbfc0eab_700x.png?v=1669970669", 
                                "https://shop.hololivepro.com/cdn/shop/products/1220__1st_StillStillStellar_4116b0b5-68d0-4648-8905-9067fba05da0_700x.png?v=1639564807", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_hololivefriends_top_en_kobo_1715332508_700x.png?v=1715332474",
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_hololivefriends_top_en_zeta_1715332640_700x.png?v=1715332585",
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_hololivefriends_top_en_kaela_1715332432_700x.png?v=1715332398"
                            };
                            for (int i = 0; i < 12; i++) {
                        %>
                        <tr>
                        <td><img class="product-image" src="<%= images[i] %>" alt="Merch Image <%=i+1%>" onerror="this.onerror=null;this.src='https://via.placeholder.com/100';"></td>
                        <td><%= descriptions[i] %><br><%= prices[i] %></td>
                        <td>
                            <div class="action-buttons">
                                <form action="product.jsp" method="post">
                                    <input class="add-button" type="submit" name="Submit" value="BUY NOW">
                                    <input type="hidden" name="id" value="<%=i%>">
                                    <input type="hidden" name="desc" value="<%= descriptions[i] %>">
                                    <input type="hidden" name="price" value="<%= prices[i].replace("IDR ", "").replace(",", "") %>">
                                </form>
                            </div>
                        </td>
                    </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
