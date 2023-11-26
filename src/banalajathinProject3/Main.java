/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package banalajathinProject3;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author manoh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    // Fields that keep track of daily activity
    // Daily activity is summarized in the report file at the end of the day
    static ArrayList<String> record = new ArrayList();
    static int totalSales = 0;
    static double totalRevenue = 0.00;
    
    public static void main(String[] args) {
        // Initialize Scanner & Bookstore objects
        // Populate inventory and member lists with default products and customers
        Scanner sc = new Scanner(System.in);
        
        Bookstore bookstore = new Bookstore();
        bookstore.generateInventory();
        bookstore.generateMembers();
        
        int operation;
        
        
        boolean keepRunning = true;
        
        while(keepRunning)
        {
            System.out.println("Welcome to Bob's Bookstore");
            System.out.println("""
                           Select one of the following options:
                           1. Make a purchase
                           2. Register as a new member
                           3. View customer list
                           4. View inventory
                           5. Restock items
                           6. Get inventory value
                           7. Exit""");
            
            try{
              operation = sc.nextInt();
                sc.nextLine();  
            }
            catch(InputMismatchException ex){
                operation = 10;
                
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e){
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
                
                operation = 10;
            }
            
            //Executes code to fulfill the function specified by user input
            switch(operation)
            {
                case 1: // Make a Purchase
                    purchase(bookstore, sc);
                    break;
                
                case 2: // Register a new member
                    registerNewMember(bookstore, sc);
                    break;
                    
                case 3: // View customer list
                    bookstore.displayMembers();
                    break;
                    
                case 4: // View inventory
                    bookstore.displayInventory();
                    break;
                    
                case 5: // Restock items
                    restock(bookstore, sc);
                    break;
                    
                case 6: // Displays total inventory value
                    System.out.print("The total value of the inventory is: $");
                    System.out.print(bookstore.inventoryValue() + "\n");
                    break;
                    
                case 7: // Exit
                    System.out.println("Have a nice day!");
                    keepRunning = false;
                    break;
                
                default: // Default case
                    System.out.println("Please enter a number from 1 to 7");
            
            }
            
        }
        
        bookstore.finalInventory();
        bookstore.finalCustomerList();
        generateReport();
        
    }
    
    // Purchase function called in the main method
    static boolean purchase(Bookstore b, Scanner sc)
    {
        double cost = 0;
        int operation = 0;
        System.out.println("Would you like to buy a book(1), CD(2), or DVD(3)?");
        
        try
        {
            operation = sc.nextInt();
        }
        catch(InputMismatchException ex)
        {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
        }
        catch(Exception e)
        {
            sc.nextLine();
            System.out.println("Unable to process request. Please try again!");
        }
        
        
        // Formats prices to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        int id;
        
        if(operation == 1) // Purchase a book
        {
            System.out.print("Would you like to buy ");
            
            // Pulls list of books from inventory file
            ArrayList<Book> bList = b.getBookInventory();
            for(int i = 0; i < bList.size(); i++)
            {
                if(i == (bList.size() - 1))
                {
                    System.out.print("or " + bList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(bList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            
            try
            {
                operation = sc.nextInt();
                id = bList.get(operation - 1).getId();
            
                cost = b.retrieve(id).getPrice();
                b.retrieve(id).deductQuantity(1);
                System.out.println("Your total is $" + df.format(cost));
            
                String newRecord = b.retrieve(id).getName() + " was purchased for $" + df.format(cost);
                record.add(newRecord);
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
            totalSales++;
            totalRevenue += cost;
            
        }
        else if(operation == 2) // Purchase a CD
        {
            System.out.print("Would you like to buy ");
            
            // Pulls list of CDs from inventory file
            ArrayList<CD> cdList = b.getCDInventory();
            for(int i = 0; i < cdList.size(); i++)
            {
                if(i == (cdList.size() - 1))
                {
                    System.out.print("or " + cdList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(cdList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            
            try
            {
                operation = sc.nextInt();
                id = cdList.get(operation - 1).getId();
            
                cost = b.retrieve(id).getPrice();
                b.retrieve(id).deductQuantity(1);
                System.out.println("Your total is $" + df.format(cost));
            
                String newRecord = b.retrieve(id).getName() + " was purchased for $" + df.format(cost);
                record.add(newRecord);
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
            totalSales++;
            totalRevenue += cost;
            
        }
        else if(operation == 3) // Purchase a DVD
        {
            System.out.print("Would you like to buy ");
            
            // Pulls list of DVDs from inventory file
            ArrayList<DVD> dvdList = b.getDVDInventory();
            for(int i = 0; i < dvdList.size(); i++)
            {
                if(i == (dvdList.size() - 1))
                {
                    System.out.print("or " + dvdList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(dvdList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            
            try
            {
                operation = sc.nextInt();
                id = dvdList.get(operation - 1).getId();
            
                cost = b.retrieve(id).getPrice();
                b.retrieve(id).deductQuantity(1);
                System.out.println("Your total is $" + df.format(cost));
            
                String newRecord = b.retrieve(id).getName() + " was purchased for $" + df.format(cost);
                record.add(newRecord);
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
            totalSales++;
            totalRevenue += cost;
            
        }
        
        System.out.println("Thank you for your purchase.");
        
        return true;
    }
    
    // Register a new member
    static boolean registerNewMember(Bookstore b, Scanner sc){
        int operation = 0;
        boolean isCustomerPremium = false;
        String customerName = " ";
        
        try{
           // Get name and memebership type of customer
            System.out.println("What is your name?");
            customerName = sc.nextLine();
            // Determine whether or not the customer would like to be a premium member
            System.out.println("Would you like to become a premium member "
                + "for 9.99 a month? Type 1 if yes and 0 if no");
            operation = sc.nextInt();
            isCustomerPremium = (operation == 1); 
        }
        catch(InputMismatchException ex)
        {
            sc.nextLine();
            System.out.println("Invalid type entered. Please try again!");
        }
        catch(Exception e)
        {
            sc.nextLine();
            System.out.println("Unable to process request. Please try again!");
        }
        
        
        // If the customer is a premium member, check if they want to pay their bill now
        if(isCustomerPremium)
        {
            PremiumMember newMember = new PremiumMember(customerName, 0.00, false);
            // Determine if the customer would like to pay their bill now
            System.out.println("Would you like to pay your bill now? "
                    + "Type 1 if yes and 0 if no");
            try
            {
                operation = sc.nextInt();
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            if(operation == 1){
                newMember.setPaid(true);
                newMember.addMoneySpent(9.99);
            }
            b.addNewPremiumMember(newMember);
            String newRecord = newMember.getName() + " was registered as a premium member who has ";
            if(newMember.hasPaid())
            {
                newRecord += "paid ";
            }
            else
            {
                newRecord+= "not paid ";
            }
            newRecord += "and spent a total of $" + newMember.getTotalSpent();
            record.add(newRecord);
        }
        else
        {
            Member newMember = new Member(customerName, 0.00);
            b.addNewMember(newMember);
            
            String newRecord = newMember.getName() + 
                    " was registered as a regular member who has spent a total of $" 
                    + newMember.getTotalSpent();
            record.add(newRecord);
            
        }
                    
        System.out.println("Thank you for your business.");
        
        return true;
    }
    
    // Restock products
    static boolean restock(Bookstore b, Scanner sc){
        System.out.println("Would you like to restock a book(1), CD(2), or DVD(3)?");
        int operation = 0;
        
        try
        {
            operation = sc.nextInt();
        }
        catch(InputMismatchException ex)
        {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
        }
        catch(Exception e)
        {
            sc.nextLine();
            System.out.println("Unable to process request. Please try again!");
        }
        
        int id = 0;
        
        if(operation == 1) // Restock a book
        {
            System.out.print("Would you like to restock ");
            ArrayList<Book> bList = b.getBookInventory();
            for(int i = 0; i < bList.size(); i++)
            {
                if(i == (bList.size() - 1))
                {
                    System.out.print("or " + bList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(bList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            try
            {
                operation = sc.nextInt();
                id = bList.get(operation - 1).getId();
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
        }
        else if(operation == 2) // Restock a CD
        {
            System.out.print("Would you like to restock ");
            ArrayList<CD> cdList = b.getCDInventory();
            for(int i = 0; i < cdList.size(); i++)
            {
                if(i == (cdList.size() - 1))
                {
                    System.out.print("or " + cdList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(cdList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            
            try
            {
                operation = sc.nextInt();
                id = cdList.get(operation - 1).getId();
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
        }
        else if(operation == 3) // Restock a DVD
        {
            System.out.print("Would you like to restock ");
            ArrayList<DVD> dvdList = b.getDVDInventory();
            for(int i = 0; i < dvdList.size(); i++)
            {
                if(i == (dvdList.size() - 1))
                {
                    System.out.print("or " + dvdList.get(i).getName() + "(" + (i+1) + ")");
                }
                else
                {
                    System.out.print(dvdList.get(i).getName() + "(" + (i+1) + ")" + ", ");
                }
                
            }
            System.out.println("?");
            
            try{
                operation = sc.nextInt();
                id = dvdList.get(operation - 1).getId();
            }
            catch(InputMismatchException ex)
            {
                sc.nextLine();
                System.out.println("Invalid type entered. Please try again!");
            }
            catch(Exception e)
            {
                sc.nextLine();
                System.out.println("Unable to process request. Please try again!");
            }
            
        }
        
        try
        {
            // Get quantity of the product to be added to inventory
            System.out.println("How many of this item would you like to restock?");
            int quantity = sc.nextInt();
        
            // Get new quantity of the product in stock
            quantity = b.retrieve(id).restock(quantity);
            System.out.println("There are " + quantity + " of this item in stock now.");
        
            String newRecord = b.retrieve(id).getName() + " was restocked " + quantity + " times";
            record.add(newRecord);
        }
        catch(InputMismatchException ex)
        {
            sc.nextLine();
            System.out.println("Invalid type entered. Please try again!");
        }
        catch(Exception e)
        {
            sc.nextLine();
            System.out.println("Unable to process request. Please try again!");
        }
        
        return true;
    }
    
    static boolean generateReport()
    {
        try
        {
            FileOutputStream fs = new FileOutputStream("Report.txt");
            PrintWriter outFS = new PrintWriter(fs);
            outFS.println("End of Day Report:");
            outFS.println();
            
            for(String r : record)
            {
                outFS.println(r);
            }
            
            outFS.println();
            outFS.println("Total Sales: " + totalSales);
            outFS.println("Total Revenue: " + totalRevenue);
            
            outFS.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Caught FileNotFoundException for outputData.txt Try again making sure the file name and path are corrrect.");
        }
        catch(IOException x){
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
 
        return true;
    }
    
}
