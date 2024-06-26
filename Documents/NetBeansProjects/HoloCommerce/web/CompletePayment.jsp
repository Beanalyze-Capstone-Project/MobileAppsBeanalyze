<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <script type="text/javascript">
        function setQuantityToZero(itemId) {
            try {
                shoppingCart.setQuantityToZero(itemId);

                // Update the display after setting the quantity to zero
                document.getElementById('quantity-' + itemId).innerText = "0";
                alert('Quantity set to zero for item ID: ' + itemId);
            } catch (e) {
                console.error('Error setting quantity to zero:', e);
            }
        }
    </script>
</head>
<body>
    <h1>Shopping Cart</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Item ID</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Example row, you would generate rows like this dynamically -->
            <tr>
                <td>item123</td>
                <td>Sample Item</td>
                <td>$10.00</td>
                <td id="quantity-item123">3</td>
                <td><button onclick="setQuantityToZero('item123')">Set Quantity to Zero</button></td>
            </tr>
            <!-- Add more rows as necessary -->
        </tbody>
    </table>
</body>
</html>
