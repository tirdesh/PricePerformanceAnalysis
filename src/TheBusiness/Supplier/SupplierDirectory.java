/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.Supplier;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class SupplierDirectory {
    ArrayList<Supplier> suppliers;
    public SupplierDirectory(){
        suppliers = new ArrayList();
    }
    public Supplier newSupplier(String n){
        Supplier supplier = new Supplier(n);
        suppliers.add(supplier);
        return supplier;

    }
    public Supplier findSupplier(String id){
        
        for (Supplier supplier: suppliers){
            
            if(supplier.getName().equals(id)) return supplier;
        }
        return null;
        }
    public ArrayList<Supplier> getSuplierList(){
        return suppliers;
    }
 
    public Supplier getRandomSupplier() {

        if (!suppliers.isEmpty()) {
            // Use a random index to select a supplier from the list
            Random random = new Random();
            int randomIndex = random.nextInt(suppliers.size());

            return suppliers.get(randomIndex);
        } else {
            System.out.println("No suppliers available.");
            return null;
        }
    }

    public ArrayList<Supplier> getSupplierList() {
        return suppliers;
    }
}