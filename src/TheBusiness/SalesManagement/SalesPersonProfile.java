/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SalesManagement;

import java.util.ArrayList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.Profile;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonProfile extends Profile {
    ArrayList<Order> salesorders;
    Person person;

    public SalesPersonProfile(Person p) {

        super(p); 
        person = p;
        salesorders = new ArrayList();

    }
    public void addSalesOrder(Order o){
        salesorders.add(o);
    }
    @Override
    public String getRole(){
        return  "Sales";
    }
    
    @Override
    public String toString(){
        return person.getPersonId();
    }

}
