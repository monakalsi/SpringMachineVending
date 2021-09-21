/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author sembh
 */
   public class VendingMachineServiceLayerImpl implements
       
     VendingMachineServiceLayer{
 
    VendingMachineAuditDao auditDao;
    VendingMachineDao dao;
    Change change;
    

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao, Change change) {
       this.dao = dao;
       this.auditDao = auditDao;
       this.change = change;
    }

      @Override
    public List<Item> getAllItems() throws
            VendingMachinePersistenceException {
       return dao.getAllItems();
    }
    
     @Override
    public Item getItem(String itemNumber) throws
            VendingMachinePersistenceException {
        return dao.getItem(itemNumber);
    }
    
    @Override
    public String buyItem(String itemNumber, BigDecimal deposit) throws
            VendingMachinePersistenceException, 
           InsufficientFundsException, 
           NoItemInventoryException {
       
        Item item = dao.getItem(itemNumber);
        
        if(item.getItemQuantity() <= 0) {
            throw new NoItemInventoryException(
                "ERROR: Item "
                + item.getItemName()
                + "is SOLD OUT");
        }
        
         if(deposit.compareTo(item.getItemPrice()) < 0) {
             throw new InsufficientFundsException("Insuffient Deposit");
         }
        
        change.makeChange(item, deposit);
        
        //subtract 1 from item inventory
        dao.buyItem(itemNumber);
        
        String stringChange = "Your recieving " + change.getDollars()+ " Dollars "
                            + "\nYour recieving " + change.getQuarters()+ " Quarters "
                     +   "\nYour recieving " + change.getDimes()+ " Dimes "
                      +   "\nYour recieving " + change.getNickels()+ " Nickels "
                        +  "\nYour recieving " + change.getPennies()+ " Pennies";

        auditDao.writeAuditEntry("Item " + item.getItemName() + " bought.");
        
        return stringChange;
         
    }

   


    
    

}