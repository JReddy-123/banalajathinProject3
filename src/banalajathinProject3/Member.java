/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banalajathinProject3;

/**
 *
 * @author manoh
 */
public class Member{
    // private instance variables, not accessible from outside this class
    private String name;
    private double totalSpent;

    // The default constructor with no argument.
    public Member(){
        name = "";
        totalSpent = 0.00;
    }
    
    public Member(String name, double totalSpent) {
        this.name = name;
        this.totalSpent = totalSpent;
    }

    public String getName() {
        return name;
    }

    public double getTotalSpent() {
        return totalSpent;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }
    
    // A public method that increases the total amount of money the customer has spent
    public void addMoneySpent(double moneySpent){
        totalSpent += moneySpent;
    }
    
    public void printCustomerInfo(){
        
        System.out.print("\n" + name + " is a regular member ");
        
        System.out.print("who has spent a total of $" + totalSpent);
        
        System.out.println();
    }
    
}
