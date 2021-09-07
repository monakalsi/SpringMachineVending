/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.monkalsi.vendingmachine.dto.Item;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;



/**
 *
 * @author sembh
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() throws NoItemInventoryException {

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            
             listItems();
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        buyItem();
                        break;
                    case 2:
                        listItems();
                        break;
                    case 3:
                        addMoney();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listItems()
            throws VendingMachinePersistenceException, NoItemInventoryException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }


    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void buyItem()
            throws VendingMachinePersistenceException, NoItemInventoryException {
       
        BigDecimal deposit = requestDeposit();

        boolean hasErrors = false;
        do {
            String itemNumber = view.getItemNumberChoice();
            try {
                String buyItem = service.buyItem(itemNumber, deposit);
                view.displayChange(buyItem);
                view.displayThankYouBuy();
                hasErrors = false;
            } catch (InsufficientFundsException e) {
               hasErrors = true;
               view.displayErrorMessage(e.getMessage());
               
               BigDecimal deposit1 = requestDeposit1();
               deposit=deposit.add(deposit1);
            }
            catch(NoItemInventoryException e){
                view.displayErrorMessage(e.getMessage());
            }
                   
            catch (Exception e ){
                view.displayErrorMessage("An error has occured");
            }
        } while (hasErrors);

    }
 
     
    
     private BigDecimal requestDeposit() {
         
        BigDecimal deposit = view.displayRequestDeposit();
        view.displayDepositSuccessful();
        
        return deposit;
         
    
     }
     
       private BigDecimal requestDeposit1() {
         
        BigDecimal deposit1 = view.displayRequestDeposit1();
        view.displayDepositSuccessful();
        
        return deposit1;
         
    
     }
     
      /* private BigDecimal requestDeposit1() {
         
        BigDecimal deposit = view.displayRequestDeposit();
        
         
        return deposit.add(deposit);
       }*/
   /* public void deposit1(double depositAmount) {
    balance += depositAmount;
    System.out.println("Your updated balance is: " + balance);*/

    private void addMoney() 
        throws VendingMachinePersistenceException, NoItemInventoryException {
       
        BigDecimal deposit = requestDeposit();

        boolean hasErrors = false;
        do {
            String itemNumber = view.getItemNumberChoice();
            try {
                String buyItem = service.buyItem(itemNumber, deposit);
                view.displayChange(buyItem);
                view.displayThankYouBuy();
                hasErrors = false;
            } catch (InsufficientFundsException e) {
               hasErrors = true;
               view.displayErrorMessage(e.getMessage());
               
               BigDecimal deposit1 = requestDeposit1();
               deposit=deposit.add(deposit1);
            }
            catch(NoItemInventoryException e){
                view.displayErrorMessage(e.getMessage());
            }
                   
            catch (Exception e ){
                view.displayErrorMessage("An error has occured");
            }
        } while (hasErrors);

    }
    }
 
     

   


