/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.monkalsi.vendingmachine.dto.Item;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sembh
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws 
            VendingMachinePersistenceException, NoItemInventoryException;
     
      Item getItem(String itemNumber) throws
            VendingMachinePersistenceException;
      
       String buyItem(String itemNumber, BigDecimal deposit) throws
            VendingMachinePersistenceException,
            InsufficientFundsException, 
            NoItemInventoryException;

       
    
}
