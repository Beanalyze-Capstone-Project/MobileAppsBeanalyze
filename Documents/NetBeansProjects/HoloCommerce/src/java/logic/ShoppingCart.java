package logic;

import java.lang.String;
import java.lang.Integer;
import java.lang.Float;
import java.util.Hashtable;
import java.util.Enumeration;

public class ShoppingCart {

    protected Hashtable<String, String[]> items = new Hashtable<>();

    /**
     * Adds an item to the shopping cart.
     * 
     * @param itemId   the ID of the item
     * @param desc     the description of the item
     * @param price    the price of the item
     * @param quantity the quantity of the item
     */
    public void addItem(String itemId, String desc, float price, int quantity) {
        if (items.containsKey(itemId)) {
            String[] tmpItem = items.get(itemId);
            int tmpQuant = Integer.parseInt(tmpItem[3]);
            quantity += tmpQuant;
            tmpItem[3] = Integer.toString(quantity);
        } else {
            String[] item = {itemId, desc, Float.toString(price), Integer.toString(quantity)};
            items.put(itemId, item);
        }
    }

    /**
     * Removes or decreases the quantity of an item from the shopping cart.
     * 
     * @param itemId the ID of the item to remove or decrease
     */
    public void removeItem(String itemId) {
        if (items.containsKey(itemId)) {
            String[] tmpItem = items.get(itemId);
            int tmpQuant = Integer.parseInt(tmpItem[3]);
            if (tmpQuant > 1) {
                tmpQuant--;
                tmpItem[3] = Integer.toString(tmpQuant);
            } else {
                items.remove(itemId);
            }
        }
    }

    /**
     * Updates the quantity of an item in the shopping cart.
     * 
     * @param itemId   the ID of the item
     * @param quantity the new quantity
     */
    public void updateQuantity(String itemId, int quantity) {
        if (items.containsKey(itemId)) {
            String[] tmpItem = items.get(itemId);
            tmpItem[3] = Integer.toString(quantity);
        }
    }

    /**
     * Sets the quantity of an item to zero.
     * 
     * @param itemId the ID of the item
     */
    public void setQuantityToZero(String itemId) {
        if (items.containsKey(itemId)) {
            String[] tmpItem = items.get(itemId);
            tmpItem[3] = "0";
        }
    }

    public Enumeration<String[]> getEnumeration() {
        return items.elements();
    }

    public float getCost() {
        Enumeration<String[]> e = items.elements();
        String[] tmpItem;
        float totalCost = 0.0f;
        while (e.hasMoreElements()) {
            tmpItem = e.nextElement();
            totalCost += (Integer.parseInt(tmpItem[3]) * Float.parseFloat(tmpItem[2]));
        }
        return totalCost;
    }

    public int getNumOfItems() {
        Enumeration<String[]> e = items.elements();
        String[] tmpItem;
        int numOfItems = 0;
        while (e.hasMoreElements()) {
            tmpItem = e.nextElement();
            numOfItems += Integer.parseInt(tmpItem[3]);
        }
        return numOfItems;
    }
}
