/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SalesManagement;

import TheBusiness.Business.Business;
import java.util.ArrayList;
import TheBusiness.Personnel.Person;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonDirectory {

    Business business;
    ArrayList<SalesPersonProfile> salespersonlist;

    public SalesPersonDirectory(Business d) {

        business = d;
        salespersonlist = new ArrayList();

    }

    public SalesPersonProfile newSalesPersonProfile(Person p) {

        SalesPersonProfile sp = new SalesPersonProfile(p);
        salespersonlist.add(sp);
        return sp;
    }

    public SalesPersonProfile findSalesPerson(String id) {

        for (SalesPersonProfile sp : salespersonlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
            return null; //not found after going through the whole list
         }

    public ArrayList getSalesPersonList() {
        return salespersonlist;
    }
    
    public SalesPersonProfile getRandomSalesPerson() {

        if (!salespersonlist.isEmpty()) {
            // Use a random index to select a salesPerson from the list
            Random random = new Random();
            int randomIndex = random.nextInt(salespersonlist.size());

            return salespersonlist.get(randomIndex);
        } else {
            System.out.println("No salesPersons available.");
            return null;
        }
    }  

}
