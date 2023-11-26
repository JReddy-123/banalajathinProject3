/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banalajathinProject3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author manoh
 */
public class Bookstore implements BookStoreSpecification{
    Scanner fileScanner;
    Scanner fileScanner2;
    
    ArrayList<Member> memberList;
    PremiumMember customer1 = new PremiumMember("Arnold Schwarzenegger", 196.53, true);
    Member customer2 = new Member("Samuel L. Jackson", 103.79);
    PremiumMember customer3 = new PremiumMember("William Smithiam", 74.28, false);
    
    private ArrayList<Book> bookList;
//    Book book1 = new Book("Percy Jackson & The Olympians Collection", 
//            "The 5-book collection of Rick Riordan's best-selling Percy Jackson series", 
//            28.25, 5, 101);
//    Book book2 = new Book("The Pelican Brief", 
//            "Read legal thriller author John Grisham's novel, 'The Pelican Brief', "
//                    + "about the assassination of 2 SCOTUS justices", 4.50, 7, 102);
//    Book book3 = new Book("1984", 
//            "Learn why Totalitarianism works in George Orwell's 1984", 10.39, 3, 103);
    
    
    private ArrayList<CD> CDList;
//    CD CD1 = new CD("Thriller", "Listen to Michael Jackson's world-famous album", 13.23, 4, 201);
//    CD CD2 = new CD("Rumours", "Listen to Fleetwood Mac's world-famous album", 11.50, 2, 202);
//    CD CD3 = new CD("Metallica(1991)", "Listen to Metallica's world-famous album", 15.97,6, 203);
    
    private ArrayList<DVD> DVDList;
//    CD CD1 = new CD("Thriller", "Listen to Michael Jackson's world-famous album", 13.23, 4, 201);
//    CD CD2 = new CD("Rumours", "Listen to Fleetwood Mac's world-famous album", 11.50, 2, 202);
//    CD CD3 = new CD("Metallica(1991)", "Listen to Metallica's world-famous album", 15.97,6, 203);    
//    DVD DVD1 = new DVD("Rush Hour 3", "Watch the highly anticipated "
//            + "final installment of the action comedy series starring "
//            + "Jackie Chan and Chris Tucker", 7.29, 2, 301);
//    DVD DVD2 = new DVD("Die Hard(1988)", "Watch Bruce Willis take "
//            + "down a dozen terrorists and free a group of unfortunate "
//            + "office workers, including his wife, from certain death", 5.99, 4, 302);
//    DVD DVD3 = new DVD("The Pelican Brief", 
//            "Based on the book by award-winning author John Grisham and "
//                    + "starring Denzel Washington & Julia Roberts", 8.68, 5, 303);
    
    // Initializes ArrayLists
    public Bookstore()
    {
        try
        {
            fileScanner = new Scanner(new File("Inventory.csv"));
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Caught FileNotFoundException for Inventory.csv Try again making sure the file name and path are corrrect.");
        }
        
        try
        {
            fileScanner2 = new Scanner(new File("Customers.csv"));
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Caught FileNotFoundException for Customers.csv Try again making sure the file name and path are corrrect.");
        }
        
        memberList = new ArrayList(3);
        bookList = new ArrayList(3);
        CDList = new ArrayList(3);
        DVDList = new ArrayList(3);
        
    }
    
    // Adds pre-generated products to inventory
    public void generateInventory(){
        try
        {
            String header = fileScanner.nextLine();
            System.out.println("Header: " + header);
        
            String[] headerValues = header.split("; ");
            for(int i = 0; i < headerValues.length; i++)
            {
                System.out.println(i + ": " + headerValues[i]);
            }
            
            String line;
            String lineValues[];
            
            String title;
            String description;
            double price;
            int quantity;
            int id;
            
            while(fileScanner.hasNext())
            {
                line = fileScanner.nextLine();
                lineValues = line.split("; ");
            
                if(lineValues[0].equalsIgnoreCase("book"))
                {
                    title = lineValues[1];
                    description = lineValues[2];
                    price = Double.parseDouble(lineValues[3]);
                    quantity = Integer.parseInt(lineValues[4]);
                    id = Integer.parseInt(lineValues[5]);
                    Book book = new Book(title, description, price, quantity, id);
                    bookList.add(book);
                }
                else if(lineValues[0].equalsIgnoreCase("cd"))
                {
                    title = lineValues[1];
                    description = lineValues[2];
                    price = Double.parseDouble(lineValues[3]);
                    quantity = Integer.parseInt(lineValues[4]);
                    id = Integer.parseInt(lineValues[5]);
                    CD cd = new CD(title, description, price, quantity, id);
                    CDList.add(cd);
                }
                else if(lineValues[0].equalsIgnoreCase("dvd"))
                {
                    title = lineValues[1];
                    description = lineValues[2];
                    price = Double.parseDouble(lineValues[3]);
                    quantity = Integer.parseInt(lineValues[4]);
                    id = Integer.parseInt(lineValues[5]);
                    DVD dvd = new DVD(title, description, price, quantity, id);
                    DVDList.add(dvd);
                }
                
            }
            
            fileScanner.close();
        }
        catch(Exception ex)
        {
            System.out.println("Please examine inventory file.");
        }
        
        
//        bookList.add(book1);
//        bookList.add(book2);
//        bookList.add(book3);
//        
//        CDList.add(CD1);
//        CDList.add(CD2);
//        CDList.add(CD3);
//        
//        DVDList.add(DVD1);
//        DVDList.add(DVD2);
//        DVDList.add(DVD3);
        
    }
    
    // Adds pre-generated members to member list
    public void generateMembers(){
        try
        {
            String header = fileScanner2.nextLine();
            System.out.println("Header: " + header);
        
            String[] headerValues = header.split("; ");
            for(int i = 0; i < headerValues.length; i++)
            {
                System.out.println(i + ": " + headerValues[i]);
            }
            
            String line;
            String lineValues[];
            
            String name;
            double totalSpent;
            boolean hasPaid;
            
            while(fileScanner2.hasNext())
            {
                line = fileScanner2.nextLine();
                lineValues = line.split("; ");
            
                if(lineValues[0].equalsIgnoreCase("regular"))
                {
                    name = lineValues[1];
                    totalSpent = Double.parseDouble(lineValues[2]);
                    Member member = new Member(name, totalSpent);
                    memberList.add(member);
                }
                else if(lineValues[0].equalsIgnoreCase("premium"))
                {
                    name = lineValues[1];
                    totalSpent = Double.parseDouble(lineValues[2]);
                    hasPaid = Boolean.parseBoolean(lineValues[3]);
                    PremiumMember member = new PremiumMember(name, totalSpent, hasPaid);
                    memberList.add(member);
                }
                
            }
            
            fileScanner2.close();
        }
        catch(Exception ex)
        {
            System.out.println("Please examine customer file.");
        }
        
//        memberList.add(customer1);
//        memberList.add(customer2);
//        memberList.add(customer3);
    }
    
    // Creates new member based on name and total spent
    public void addNewMember(String name, int totalSpent){
        Member newMember = new Member(name, totalSpent);
        memberList.add(newMember);
    }
    
    // Adds new member based on member object passed to the method
    public void addNewMember(Member m){
        memberList.add(m);
    }
    
    public void addNewPremiumMember(String name, int totalSpent, boolean hasPaid){
        PremiumMember newMember = new PremiumMember(name, totalSpent, hasPaid);
        memberList.add(newMember);
    }
    
    public void addNewPremiumMember(PremiumMember pm){
        memberList.add(pm);
    }
    
    public ArrayList<Book> getBookInventory(){
        return bookList;
    }
    
    public ArrayList<CD> getCDInventory(){
        return CDList;
    }
    
    public ArrayList<DVD> getDVDInventory(){
        return DVDList;
    }
    
    public ArrayList<Member> getMemberList(){
        return memberList;
    }
    
    //    public ArrayList<PremiumMember> getPremiumMemberList(){
//        return premiumMemberList;
//    }
    
    public void displayMembers()
    {
        System.out.println("\n" + "List of regular members: ");
        for(Member m : memberList)
        {
            if(!(m instanceof PremiumMember))
            {
                System.out.println(m.getName() + " has spent a total of $" + m.getTotalSpent());
            }
            
        }
        
        System.out.println("\n" + "List of premium members: ");
        for(Member m : memberList)
        {
            if(m instanceof PremiumMember)
            {
                System.out.print(m.getName() + " has spent a total of $" 
                        + m.getTotalSpent() + " and they have ");
                if(((PremiumMember) m).hasPaid())
                {
                    System.out.print("paid." + "\n");
                }
                else
                {
                    System.out.print("not paid." + "\n");
                }
            }
            
        }
        
        System.out.println();
    }
    
    public void displayInventory(){
        System.out.println("\n" + "List of Books");
        for(Book book : bookList)
        {
            System.out.println("There are " + book.getQuantity() + " copies of "
                    + book.getName() + " in stock at $" + book.getPrice());
        }
        
        System.out.println("\n" + "List of CDs");
        for(CD cd : CDList)
        {
            System.out.println("There are " + cd.getQuantity() + " copies of "
                    + cd.getName() + " in stock at $" + cd.getPrice());
        }
        
        System.out.println("\n" + "List of DVDs");
        for(DVD dvd : DVDList)
        {
            System.out.println("There are " + dvd.getQuantity() + " copies of "
                    + dvd.getName() + " in stock at $" + dvd.getPrice());
        }
        
        System.out.println();
    }
    
    
//    public void decrementInventory(int amount){
//        
//    }
    
//    public String getCartItem(int amount){
//        
//    }
    
//    public void displayMemberStatus(int amount){
//        
//    }
    
    public Product retrieve(int id)
    {
        if(id > 300)
        {
            for(DVD dvd : DVDList)
            {
                if(dvd.getId() == id){
                    return dvd;
                }     
            }
        }
        
        if(id > 200)
        {
            for(CD cd : CDList)
            {
                if(cd.getId() == id){
                    return cd;
                }     
            }
        }
        
        if(id > 100)
        {
            for(Book book : bookList)
            {
                if(book.getId() == id){
                    return book;
                }     
            }
        }
        
        Book bookNull = new Book("Null", "Product not found", 0.00, 0, 0);
        return bookNull;
    }

    @Override
    public int restockProduct(int productId, int amount) {
        int currentQuantity = retrieve(productId).restock(amount);
        
        // returns quantity after restocking is performed
        return currentQuantity;
    }

    @Override
    public double inventoryValue() {
        double totalCost = 0.00;
        for(Book b : bookList)
        {
            totalCost += (b.getPrice())*(b.getQuantity());
        }
        for(CD c : CDList)
        {
            totalCost += (c.getPrice())*(c.getQuantity());
        }
        for(DVD d : DVDList)
        {
            totalCost += (d.getPrice())*(d.getQuantity());
        }
        return totalCost;
    }
    
    public void finalInventory()
    {
        try
        {
            FileOutputStream fs = new FileOutputStream("UpdatedInventory.csv");
            PrintWriter outFS = new PrintWriter(fs);
            outFS.println("Type; Title; Description; Price; Quantity; ID");
            
            for(Book book : bookList)
            {
                outFS.print("Book; " + book.getName() + "; " + book.getDescription() + "; "+ book.getPrice() + "; " + book.getQuantity() + "; " + book.getId());
                outFS.println();
            }
            for(CD cd : CDList)
            {
                outFS.print("CD; " + cd.getName() + "; " + cd.getDescription() + "; "+ cd.getPrice() + "; " + cd.getQuantity() + "; " + cd.getId());
                outFS.println();
            }
            for(DVD dvd : DVDList)
            {
                outFS.print("DVD; " + dvd.getName() + "; " + dvd.getDescription() + "; "+ dvd.getPrice() + "; " + dvd.getQuantity() + "; " + dvd.getId());
                outFS.println();
            }
            
            
            outFS.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Caught FileNotFoundException for UpdatedInventory.csv Try again making sure the file name and path are corrrect.");
        }
        catch(IOException x){
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
        
    }
    
    public void finalCustomerList()
    {
        try
        {
            FileOutputStream fs = new FileOutputStream("UpdatedCustomers.csv");
            PrintWriter outFS = new PrintWriter(fs);
            outFS.println("Membership Type; Name; Total Spent; Has Paid");
            
            for(Member m : memberList)
            {
                if(m instanceof PremiumMember)
                {
                    outFS.println("Premium; " + m.getName() + "; " + m.getTotalSpent() + "; " + ((PremiumMember) m).hasPaid());
                }
                else
                {
                    outFS.println("Regular; " + m.getName() + "; " + m.getTotalSpent() + "; " + "N/A");
                }
            }
            
            outFS.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Caught FileNotFoundException for UpdatedCustomers.csv Try again making sure the file name and path are corrrect.");
        }
        catch(IOException x){
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
    }
        
    
    
    
}
