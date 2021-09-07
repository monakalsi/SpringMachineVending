/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.monkalsi.vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
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
public class VendingMachineDaoTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl("TESTreciept.txt");
    File file = new File("TESTreciept.txt");
    
    Item item = new Item("1");
    
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
     @BeforeAll
    public void setUp() throws UnsupportedEncodingException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TESTreciept.txt"), "utf-8"))) {
            writer.write("1::Water::1.25::10"
                    + "\n2::Sprite::1.75::11"
                    + "\n3::Mountain Dew::3.75::4"
            + "\n4::Coke::2.50::11"
            +"\n5::Kitkat::1.25::0"
            +"\n6::Dry Nuts::2.40::13"
            +"\n7::Lays::2.25::7");
        }
        
        
     
        
        item.setItemName("Water");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
    }
    
    @AfterAll
    public void tearDown() {
    
    file.delete();
    
        if(file.exists())
        {
            System.out.println("Failed to delete the file");
        }
        else
        {
          
            System.out.println("File deleted successfully");
        }
    }

    
     // Test of getAllItems method, of class VendingMachineDao.
     
    @Test
    public void testListAllItems() throws Exception {
        List<Item> allItems = dao.getAllItems();
        assertEquals(3, allItems.size());
    }

    
     //Test of getItem method, of class VendingMachineDao.
     
    @Test
    public void testGetItem() throws Exception {
        
        assertEquals(item, dao.getItem("1"));
    }

    
     // Test of purchaseItem method, of class VendingMachineDao.
     
    @Test
    public void testPurchaseItem() throws Exception {
        dao.buyItem("1");
        assertEquals(9, dao.getItem("1").getItemQuantity());
    }
 
}
