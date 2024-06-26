<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<jsp:useBean id="cart" scope="session" class="logic.ShoppingCart" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HoloStore</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <link rel="stylesheet" href="RSC/rschmpg.css">
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
              <li>
                  <a href="cart.jsp" style="color:#fff;">
                      Total Items in Cart: <%= (cart != null) ? cart.getNumOfItems() : 0 %>
                  </a>
              </li>
            </ul>
        </nav>
        <div class="container">
            <div class="fcontent">
                <img src="RSC/HoloStore.png" alt="Sample Image">
            </div>
        </div>
        <div class="content" id="#product">
            <h1>Merchandise</h1>
            <div class="product-container">
                <h2>Most Liked</h2>
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
                                "Itel S23", "Infinix note 30", "Infinix note 30 pro", 
                                "Xiaomi 13T", "Poco X6", "Poco F5", 
                                "Infinix Hot 40"
                            };

                            String[] prices = {
                                "IDR 400,000", "IDR 450,000", "IDR 350,000", 
                                "Rp 1,360,000", "Rp 2,000,000", "Rp 2,600,000", 
                                "Rp 6,500,000", "Rp 5,000,000", "Rp 4,750,000", 
                                "Rp 1,000,000"
                            };

                            String[] images = {
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_justice_debutbanner_240618_en_3_1718796657_700x.png?v=1718795797", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_regloss_2_banner_240527_en_3_1718009352_700x.png?v=1718008597", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/files/ly_holo_kaela_pemaloe_banner_240529_en_3_1717558730_700x.png?v=1717558419", 
                                "https://cdn.shopify.com/s/files/1/0529/2641/5045/t/23/assets/vestiazeta_2022_tanpin01_en_1101_1667827183_700x.png?v=1667312464", 
                                "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2023/7/5/c83c9868-e914-4918-af54-feafc723ed0a.jpg", 
                                "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/8/21/d5fcd82f-9d41-4d1f-8193-f8e5952b5134.jpg", 
                                "https://images.tokopedia.net/img/cache/900/VqbcmM/2023/10/12/bc4c8681-2393-4aab-b115-8509a5d4a146.png", 
                                "https://images.tokopedia.net/img/cache/900/VqbcmM/2024/5/27/aae9581e-d466-4ef7-8fef-4f3582600ea3.jpg", 
                                "https://images.tokopedia.net/img/cache/900/VqbcmM/2024/6/13/2e31f8dc-6642-4848-bada-38e7335fa1c2.jpg", 
                                "https://images.tokopedia.net/img/cache/900/VqbcmM/2024/1/31/72bbb598-8f4c-43e1-8823-c8bc12b8a38e.jpg"
                            };
                            for (int i = 0; i < 3; i++) {
                        %>
                        <tr>
                        <td><img class="product-image" src="<%= images[i] %>" alt="Watch Image <%=i+1%>" onerror="this.onerror=null;this.src='https://via.placeholder.com/100';"></td>
                        <td><%= descriptions[i] %><br><%= prices[i] %></td>
                        <td>
                            <div class="action-buttons">
                                <form action="homepage.jsp" method="post">
                                    <input class="add-button" type="submit" name="Submit" value="Buy Now">
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
                <div class="mmerch" style="text-align: end;">
                    <h1> <a href="product.jsp" style="text-decoration:none; color: #3399ff;">More Merch >></a></h1>
                </div>
            </div>
        </div>
        <div id="About" class="contentabout">
            <h1>About us</h1>
            <p style="color: #fff; font-size: large;"> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tempus accumsan porttitor. 
                Vestibulum sit amet pellentesque libero. Aenean metus lectus, interdum sed malesuada eget, dictum id nunc. 
                Nullam rutrum elementum arcu ut suscipit. Integer pulvinar justo bibendum, dapibus nisl eget, convallis ante. 
                Fusce a felis nec ex accumsan blandit. Praesent ac orci id massa facilisis rhoncus nec et dolor. 
                Donec interdum non neque eu viverra. Mauris fringilla mattis lacus, ut auctor lacus bibendum quis. 
                In hac habitasse platea dictumst. Sed fringilla diam quis eros volutpat, a molestie ante imperdiet.
                Pellentesque non erat auctor lectus condimentum accumsan. Duis eget ex quis mi fringilla tristique. 
                Vestibulum rhoncus vulputate augue, sit amet pretium dui consectetur vel. Nullam in posuere mauris. 
                Nullam sit amet leo eget nulla ullamcorper posuere. Praesent dictum elit eu ex pretium, nec fermentum lorem sodales. 
                In faucibus ligula sem, vel varius purus placerat et. Maecenas semper libero fringilla iaculis hendrerit. 
                In sit amet tellus quam. Duis sed ultricies nunc. Etiam eget cursus velit, non tincidunt nibh. </p>
        </div>
        <div id="Contact" class="contentcontact">
            <h1>Contact</h1>
            <h1>
            Email : 
            HoloStore@gmail.com
            Phone Number</h1>
            
        </div>
        <script>
        document.addEventListener("DOMContentLoaded", function() {
            const observer = new IntersectionObserver(entries => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        entry.target.classList.add('visible');
                    } else {
                        entry.target.classList.remove('visible');
                    }
                });
            });

            document.querySelectorAll('.content').forEach(content => {
                observer.observe(content);
            });
            document.querySelectorAll('.contentabout').forEach(content => {
                observer.observe(content);
            });
            document.querySelectorAll('.contentcontact').forEach(content => {
                observer.observe(content);
            });
        });
    </script>
    </body>
</html>
