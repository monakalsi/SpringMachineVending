/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkalsi.vendingmachine.dto;

import java.math.BigDecimal;


/**
 *
 * @author sembh
 */
public class Item {
    private String itemNumber;
    private String itemName;
    private BigDecimal itemPrice;
    public int itemQuantity;

    public Item(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int buyItem(){
     return itemQuantity--;
    }
    
   
}