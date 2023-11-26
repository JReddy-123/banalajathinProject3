/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banalajathinProject3;

/**
 *
 * @author manoh
 */
public abstract class Product implements Comparable<Product>{
    // private instance variables, not accessible from outside this class
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int id;
    
    // The default constructor with no argument.
    public Product(){
        
    }
    
    // 2nd constructor with given radius and color
    /** 
    * @param name represents the name of the product
    * @param description represents a short description of the product
    * @param price represents the cost of the product
    * @param quantity represents how many of this product are in stock
    * @param id represents the product's unique id
    */
    public Product(String name, String description, double price, int quantity, int id){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }
    
    // A public method for retrieving the product's name
    /** @return     returns the name of the product */
    public String getName(){
        return name;
    }
    
    // A public method for retrieving the product's description
    /** @return     returns a description of the product */
    public String getDescription(){
        return description;
    }
    
    // A public method for retrieving the product's price
    /** @return     returns the price of the product */
    public double getPrice(){
        return price;
    }
    
    // A public method for retrieving how many of this product are in stock
    /** @return     returns the number of items of this product still in stock */
    public int getQuantity(){
        return quantity;
    }
    
    // A public method for retrieving tthe product's unique id
    /** @return     returns the product's id */
    public int getId(){
        return id;
    }
    
    // A public method for setting the price of the product
    /** @return     returns the new price of the product*/
    public double setPrice(double newPrice){
        price = newPrice;
        return price;
    }
    
    // A public method for setting the product's id
    /** @return     returns the new id of the product*/
    public int setId(int newId){
        id = newId;
        return id;
    }
    
    // A public method that decreases the total number of this product in stock
    /** @return     returns the updated quantity of the product*/
    public int deductQuantity(int amount){
        quantity -= amount;
        return quantity;
    }
    
    // A public method that increases the total number of this product in stock
    /** @return     returns the updated quantity of the product*/
    public int restock(int restockAmount){
        if(restockAmount > 0)
            quantity += restockAmount;
        else
            System.out.println("You must add at least 1 item");
        
        return quantity;
    }
    
    // A public method that displays all information about the product
    public void printProductInfo(){
        System.out.println(name + ": " + description);
        System.out.println("There are " + quantity + " copies of " + name + 
                " in stock at $" + price + "\n");
    }
    
    @Override
    public int compareTo(Product o) {
        double thisPrice = this.getPrice();
        double otherPrice = o.getPrice();
        System.out.println("Price of Products: " + thisPrice + " " + otherPrice);
        
        if(thisPrice > otherPrice)
            return 1;
        if(thisPrice < otherPrice)
            return -1;
        if(thisPrice == otherPrice)
            return 0;
        
        return 0;
   }
}
