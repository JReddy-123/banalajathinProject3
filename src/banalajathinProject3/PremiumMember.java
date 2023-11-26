/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banalajathinProject3;

/**
 *
 * @author manoh
 */
public class PremiumMember extends Member{
    private boolean hasPaid;
    
    public PremiumMember()
    {
        super();
        hasPaid = false;
    }
    
    public PremiumMember(String name, double totalSpent, boolean hasPaid)
    {
        super(name, totalSpent);
        this.hasPaid = hasPaid;
    }

    public boolean hasPaid() {
        return hasPaid;
    }

    public void setPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
    
    @Override
    public void printCustomerInfo(){
        System.out.print("\n" + this.getName() + " is a premium member ");
        
        if(hasPaid){
            System.out.print("who has paid and spent a total of $" + this.getTotalSpent());
        }
        else{
            System.out.print("who has not paid and has spent a total of $" + this.getTotalSpent());
        }
        
        System.out.println();
    }
    
}
