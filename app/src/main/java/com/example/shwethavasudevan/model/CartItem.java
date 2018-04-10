package com.example.shwethavasudevan.model;

/**
 * Created by shwethavasudevan on 08/04/18.
 */

public class CartItem {
    public String productName;
    public String quantity;

    public CartItem(String productName, String quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

}
