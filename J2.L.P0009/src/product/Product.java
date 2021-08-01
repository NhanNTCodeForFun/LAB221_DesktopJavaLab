/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

/**
 *
 * @author Admin
 */
public class Product {
    String productName;
    String category;
    String productID;
    int quantity;
    double price;
    boolean hasUpdate;

    public Product(String productName, String productID, int quantity, double price) {
        this.productName = productName;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.hasUpdate = false;
    }

    public Product(String productName, String category, String productID, int quantity, double price) {
        this.productName = productName;
        this.category = category;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.hasUpdate = false;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    
    public Product(){
        
    }

    public String getProductName() {
        return productName;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean HasUpdate() {
        return hasUpdate;
    }

    public void setUpdateState(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }
    
    
}
