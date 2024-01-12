/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.SolutionOrders.SolutionOrderSummary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tirdesh
 */
public class SolutionHelper {
        
    private static Business business;  // Class variable to store the Business object
    private static final Random random = new Random();

    public SolutionHelper(Business business) {
        this.business = business;
        createRandomSolutionOffers(20);
        AssignRandomMarketsToCustomers();
        generateRandomSolutionOrders();
 
    }

    public static void createRandomSolutionOffers(int numberOfOffers) {

        for (MarketChannelAssignment mca : business.getMarketChannelComboCatalog().getMcalist()) {
            for (int i = 0; i < random.nextInt(numberOfOffers); i++) {
                SolutionOffer solutionOffer = business.getSolutionOfferCatalog().newSolutionOffer(mca);
                mca.addSolutionOffer(solutionOffer);
                int numberOfProducts = random.nextInt(5) + 1; // Example: 1 to 5 products
                for (int j = 0; j < numberOfProducts; j++) {
                    solutionOffer.addProduct(business.getSupplierDirectory().getRandomSupplier().getProductCatalog().getRandomProduct());
                }
                //solutionOffer.displayPrices();
                int randomSolutionPrice = random.nextInt(solutionOffer.getCeilingPrice() - solutionOffer.getTargetPrice() + 1) + solutionOffer.getTargetPrice();
                solutionOffer.setTotalPrice(randomSolutionPrice);
            }   

        }
    }
    
    public static void AssignRandomMarketsToCustomers() {
        for (CustomerProfile customer : business.getCustomerDirectory().getCustomerList()) {
            // Assign random markets to the customer
            ArrayList<Market> markets = business.getMarketCatalog().getMarkets();
            int numberOfMarkets = random.nextInt(markets.size()) + 1;  // Choose a random number of markets (1 to total number)

            // Shuffle the list of all markets and select the first 'numberOfMarkets'
            List<Market> shuffledMarkets = new ArrayList<>(markets);
            Collections.shuffle(shuffledMarkets);
            
            for (int i = 0; i < numberOfMarkets; i++) {
                customer.addMarket(shuffledMarkets.get(i));
            }
        }
    }
    
    public static void generateRandomSolutionOrders() {

        for (int i = 0; i < 50; i++) {

            // Get a random Customer
            CustomerProfile randomCustomer = business.getCustomerDirectory().getRandomCustomer();
            ArrayList<Market> markets = randomCustomer.getMarkets();

            // Get a random SalesPerson
            SalesPersonProfile randomSalesPerson = business.getSalesPersonDirectory().getRandomSalesPerson();

            // Get a random MarketChannelAssignment
            MarketChannelAssignment randomMarketChannelAssignment = business.getMarketChannelComboCatalog().getRandomMarketChannelCombo(markets.get(random.nextInt(markets.size())));
            ArrayList<SolutionOffer> sol = randomMarketChannelAssignment.getSolutionofferlist();
            SolutionOffer randomSolutionOffer = sol.get(random.nextInt(sol.size()));
            SolutionOrder solutionOrder = business.getMasterSolutionOrderList().newSolutionOrder(randomSolutionOffer, randomMarketChannelAssignment, randomCustomer, randomSalesPerson);

            // Add SolutionOrderItems with random pricing
            int numberOfItems = random.nextInt(5) + 1; // Example: 1 to 5 items
            for (int j = 0; j < numberOfItems; j++) {
                // Assume random pricing between target and ceiling price
                int randomPaidPrice = random.nextInt(randomSolutionOffer.getCeilingPrice() - randomSolutionOffer.getTargetPrice() + 1) + randomSolutionOffer.getTargetPrice();
                int randomQuantity = random.nextInt(10) + 1; // Example: 1 to 10 quantity
                solutionOrder.addSolutionOrderItem(randomSolutionOffer, randomPaidPrice, randomQuantity);
            }
            numberOfItems = random.nextInt(5) + 1; // Example: 1 to 5 items
            for (int j = 0; j < numberOfItems; j++) {
                int randomPaidPrice = random.nextInt(randomSolutionOffer.getCeilingPrice() - randomSolutionOffer.getFloorPrice()+ 1) + randomSolutionOffer.getFloorPrice();
                int randomQuantity = random.nextInt(10) + 1; // Example: 1 to 10 quantity
                solutionOrder.addSolutionOrderItem(randomSolutionOffer, randomPaidPrice, randomQuantity);
            }
            numberOfItems = random.nextInt(5) + 1; // Example: 1 to 5 items
            for (int j = 0; j < numberOfItems; j++) {
                int randomPaidPrice = random.nextInt(randomSolutionOffer.getTargetPrice() - randomSolutionOffer.getFloorPrice()+ 1) + randomSolutionOffer.getFloorPrice();
                int randomQuantity = random.nextInt(10) + 1; // Example: 1 to 10 quantity
                solutionOrder.addSolutionOrderItem(randomSolutionOffer, randomPaidPrice, randomQuantity);
            }
            
            numberOfItems = random.nextInt(5) + 1; // Example: 1 to 5 items
            for (int j = 0; j < numberOfItems; j++) {
                int randomPaidPrice = random.nextInt(randomSolutionOffer.getTargetPrice() - randomSolutionOffer.getFloorPrice() + 1) + randomSolutionOffer.getFloorPrice();
                // Ensure some items are sold below the target price
                randomPaidPrice = Math.min(randomPaidPrice, randomSolutionOffer.getTargetPrice() - 1);
                int randomQuantity = random.nextInt(10) + 1; // Example: 1 to 10 quantity
                solutionOrder.addSolutionOrderItem(randomSolutionOffer, randomPaidPrice, randomQuantity);
            }
            //SolutionOrderSummary sos = new SolutionOrderSummary(solutionOrder);
            //sos.displaySummary();
        }
    }
        
}
