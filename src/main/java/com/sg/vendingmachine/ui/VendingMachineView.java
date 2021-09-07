/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.sg.vendingmachine.ui;

import com.monkalsi.vendingmachine.dto.Change;
import com.monkalsi.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
*
* @author sembh
*/
public class VendingMachineView {

private UserIO io;
private Change change;

public VendingMachineView(UserIO io) {
    this.io = io;
}

public int printMenuAndGetSelection() {
    io.print("Main Menu");
    io.print("1. Purchase Item");
    io.print("2. List Item");
    io.print("3. Add Money");
    io.print("4. Exit");

    return io.readInt("Please select from the above choices.", 1, 3);
}

public void displayItemList(List<Item> itemList) {
for (Item currentItem : itemList) {
    if((currentItem.getItemQuantity()) <= 0){
        io.print("");
    }else{
        io.print(currentItem.getItemNumber() + ": "
                + currentItem.getItemName() + " "


                + currentItem.getItemPrice() + " "
                + currentItem.getItemQuantity());
    }
};
io.readString("Please hit enter to continue.");
}

public void displayItem(Item item) {
        if (item != null) {
            io.print(item.getItemNumber());
            io.print(item.getItemName() + " " + item.getItemName());
            io.printBigDecimal(item.getItemPrice());
            io.printInt(item.getItemQuantity());
            io.print("");
        } else {
            io.print("No such Item.");
        }
        io.readString("Please hit enter to continue.");
    }

public void displayItemBanner() {
    io.print("=== Item ===");
}

public void displayItembuyBanner() {
    io.print("=== Purchase Item ===");
}

public String getItemNumberChoice() {
    return io.readString("Please enter the Item Number.");
}

public String displayThankYouBuy() {
    return io.readString("Thank You for your purchase.");
}

public void displayDisplayAllBanner() {
    io.print("=== All Items ===");
}

public void displayExitBanner() {
    io.print("Good Bye!!!");
}

public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}

public BigDecimal displayRequestDeposit(){
    String cash = io.readString("Please deposit");
    BigDecimal bd = new BigDecimal(cash);

    return bd; 
}


public void displayDepositSuccessful(){
    io.print(" Deposit is successful");
}

public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

public void displayChange(String change) {
    io.print("Your change is " + change);
}

public BigDecimal displayRequestDeposit1() {
    String cash = io.readString("Please deposit more money to get that item");
    BigDecimal bd = new BigDecimal(cash);
    return bd;
}

}	
