/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.monkalsi.vendingmachine.dto.Change;
import com.monkalsi.vendingmachine.dto.Item;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sembh
 */
public class VendingMachineServiceLayerTest {
     VendingMachineDao dao = new VendingMachineDaoFileImpl("TESTreciept.txt");
    File file = new File("TESTreciept.txt");
    
    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
    Change change = new Change();
    VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao, change);
    Item item = new Item("1");
    
    public VendingMachineServiceLayerTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
   @BeforeAll
    public void setUp() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TESTreciept.txt"), "utf-8"))) {
            writer.write("1::Water::1.50::10"
                    + "\n2::Sprite::1.75::10"
                    + "\n3::Mountain Dew::1.75::4"
            + "\n4::Coke::1.50::10"
            +"\n5::Kitkat::1.25::0"
            +"\n6::Dry Nuts::2.00::8"
            +"\n7::Lays::1.25::7");
        }
    
        
        
        item.setItemName("Water");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
    }
    
    @AfterAll
    public void tearDown() {
        file.delete();
    
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = service.getAllItems();
        assertEquals(7, allItems.size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
          assertEquals(item, service.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItem() throws Exception {
      String buyItem = service.buyItem("1", new BigDecimal("2.00"));
       assertEquals( "Your recieving 0 Dollars \nYour recieving 2 Quarters \nYour recieving 0 Dimes \nYour recieving 0 Nickels \nYour recieving 0 Pennies", buyItem);
    }
    
    @Test 
    public void testPurchaseItemInsufficientFundsExp() throws Exception {
        try{
          service.buyItem("1", new BigDecimal(".25"));
        }catch (InsufficientFundsException e) {
        }
    }
    
    @Test
    public void testNoInventoryExp() throws Exception {
       try{
       service.buyItem("3", new BigDecimal("5.00"));
       } catch (NoItemInventoryException e){
           
       }
    }
    
}
