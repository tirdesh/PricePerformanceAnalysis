/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.SolutionOrders.SolutionOrderItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author tirdesh
 */
public class SalesAnalyticsReport {

    // Assuming you have a list of SolutionOrders
    private Business business;

    public SalesAnalyticsReport(Business business) {
        this.business = business;
    }

    public void generateReport() {
        Top3NegotiatedSolutions();
        Top3Customers();
        Top3CustomersByMarket();
        Top3SalesPeople();
        calculateMarginalRevenueByMarket();
        displayMostSellingPricesForAllSolutionOffers();
        displayLossBySolutionOffering();
    }

    // Method to generate the Sales Analytics Report
    public void Top3NegotiatedSolutions() {
        // Get the list of markets
        List<Market> markets = business.getMarketCatalog().getMarkets();

        // Iterate through each market
        for (Market market : markets) {
            // Map to store aggregated revenues for each solution offer in the current market
            Map<SolutionOffer, Integer> solutionOfferRevenues = new HashMap<>();

            // Filter solution orders for the current market
            List<SolutionOrder> marketSolutionOrders = getSolutionOrdersForMarket(market);

            // Aggregate revenues for each solution offer in the current market
            for (SolutionOrder solutionOrder : marketSolutionOrders) {
                for (SolutionOrderItem orderItem : solutionOrder.getSolutionOrderItems()) {
                    SolutionOffer solutionOffer = orderItem.getSelectedSolutionOffer();
                    int revenue = orderItem.getSolutionOrderItemTotal();

                    // Only consider solutions selling above target price
                    if (revenue > solutionOffer.getTargetPrice() * orderItem.getQuantity()) {
                        // Update the aggregated revenue for the solution offer
                        solutionOfferRevenues.put(solutionOffer, solutionOfferRevenues.getOrDefault(solutionOffer, 0) + revenue);
                    }
                }
            }

            // Sort solution offers by revenue in descending order
            List<Map.Entry<SolutionOffer, Integer>> sortedSolutionOffers = new ArrayList<>(solutionOfferRevenues.entrySet());
            sortedSolutionOffers.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

            // Display the top 3 negotiated solutions for the current market
            int count = 0;
            System.out.print("================================");
            System.out.print("Top 3 Negotiated Solutions for Market: " + market.toString());
            System.out.println("===============================");
            for (Map.Entry<SolutionOffer, Integer> entry : sortedSolutionOffers) {
                if (count >= 3) {
                    break; // Stop after displaying the top 3
                }

                SolutionOffer solutionOffer = entry.getKey();
                int revenue = entry.getValue();

                // Display information (you can customize the output based on your needs)
                System.out.println("Rank: " + (count + 1));
                System.out.println("Solution Offer: " + solutionOffer.toString());
                System.out.println("Revenue: $" + revenue);
                System.out.println("------------------------------");
                System.out.println();

                count++;
            }
        }
    }

    // Helper method to get solution orders associated with a specific market
    private List<SolutionOrder> getSolutionOrdersForMarket(Market market) {
        List<SolutionOrder> marketSolutionOrders = new ArrayList<>();
        for (SolutionOrder solutionOrder : business.getMasterSolutionOrderList().getSolutionorderlist()) {
            if (solutionOrder.getMarketChannelCombo().getMarket() == market) {
                marketSolutionOrders.add(solutionOrder);
            }
        }
        return marketSolutionOrders;
    }

    public void Top3Customers() {
        // Get the list of customers
        List<CustomerProfile> customers = business.getCustomerDirectory().getCustomerList();

        // Map to store aggregated revenues for each customer
        Map<CustomerProfile, Integer> customerRevenues = new HashMap<>();

        // Iterate through each customer
        for (CustomerProfile customer : customers) {
            int totalRevenue = 0;

            // Filter solution orders for the current customer
            List<SolutionOrder> customerSolutionOrders = getSolutionOrdersForCustomer(customer);

            // Aggregate revenues for each solution order in the current customer
            for (SolutionOrder solutionOrder : customerSolutionOrders) {
                // Only consider solution orders above target price
                if (solutionOrder.isSolutionOrderAboveTotalTarget()) {
                    totalRevenue += solutionOrder.getSolutionOrderTotal();
                }
            }

            // Update the aggregated revenue for the customer
            customerRevenues.put(customer, totalRevenue);
        }

        // Sort customers by revenue in descending order
        List<Map.Entry<CustomerProfile, Integer>> sortedCustomers = new ArrayList<>(customerRevenues.entrySet());
        sortedCustomers.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Display the top 3 customers
        int count = 0;
        System.out.print("================================");
        System.out.print("Top 3 Customers with Solution Orders Above Target:");
        System.out.println("================================");
        for (Map.Entry<CustomerProfile, Integer> entry : sortedCustomers) {
            if (count >= 3) {
                break; // Stop after displaying the top 4
            }

            CustomerProfile customer = entry.getKey();
            int totalRevenue = entry.getValue();

            // Display information for each customer
            System.out.println("Customer: " + customer.toString());
            System.out.println("Total Revenue: $" + totalRevenue);
            System.out.println();

            count++;
        }
    }

    private List<SolutionOrder> getSolutionOrdersForCustomer(CustomerProfile customer) {
        // Filter solution orders for the given customer
        return business.getMasterSolutionOrderList().getSolutionorderlist().stream()
                .filter(order -> order.getCustomerProfile() == customer)
                .collect(Collectors.toList());
    }

    public void Top3SalesPeople() {
        // Get the list of salespeople
        List<SalesPersonProfile> salespeople = business.getSalesPersonDirectory().getSalesPersonList();

        // Map to store aggregated revenues for each salesperson
        Map<SalesPersonProfile, Integer> salespersonRevenues = new HashMap<>();

        // Iterate through each salesperson
        for (SalesPersonProfile salesperson : salespeople) {
            int totalRevenue = 0;

            // Filter solution orders for the current salesperson
            List<SolutionOrder> salespersonSolutionOrders = getSolutionOrdersForSalesPerson(salesperson);

            // Aggregate revenues for each solution order in the current salesperson
            for (SolutionOrder solutionOrder : salespersonSolutionOrders) {
                // Only consider solution orders above target price
                if (solutionOrder.isSolutionOrderAboveTotalTarget()) {
                    totalRevenue += solutionOrder.getSolutionOrderTotal();
                }
            }

            // Update the aggregated revenue for the salesperson
            salespersonRevenues.put(salesperson, totalRevenue);
        }

        // Sort salespeople by revenue in descending order
        List<Map.Entry<SalesPersonProfile, Integer>> sortedSalespeople = new ArrayList<>(salespersonRevenues.entrySet());
        sortedSalespeople.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Display the top 3 salespeople
        int count = 0;
        System.out.print("================================");
        System.out.print("Top 3 Salespeople with Solution Orders Above Target:");
        System.out.println("================================");
        for (Map.Entry<SalesPersonProfile, Integer> entry : sortedSalespeople) {
            if (count >= 3) {
                break; // Stop after displaying the top 3
            }

            SalesPersonProfile salesperson = entry.getKey();
            int totalRevenue = entry.getValue();

            // Display information for each salesperson
            System.out.println("Salesperson: " + salesperson.toString());
            System.out.println("Total Revenue: $" + totalRevenue);
            System.out.println();

            count++;
        }
    }

    private List<SolutionOrder> getSolutionOrdersForSalesPerson(SalesPersonProfile salesperson) {
        // Filter solution orders for the given salesperson
        return business.getMasterSolutionOrderList().getSolutionorderlist().stream()
                .filter(order -> order.getSalesPersonProfile() == salesperson)
                .collect(Collectors.toList());
    }

    public void calculateMarginalRevenueByMarket() {
        // Get the list of markets
        List<Market> markets = business.getMarketCatalog().getMarkets();

        // Map to store aggregated marginal revenues for each market
        Map<Market, Integer> marginalRevenuesByMarket = new HashMap<>();

        // Iterate through each market
        for (Market market : markets) {
            int totalMarginalRevenue = 0;

            // Filter solution orders for the current market
            List<SolutionOrder> marketSolutionOrders = getSolutionOrdersForMarket(market);

            // Calculate marginal revenue for each solution order item in the current market
            for (SolutionOrder solutionOrder : marketSolutionOrders) {
                for (SolutionOrderItem orderItem : solutionOrder.getSolutionOrderItems()) {
                    // Only consider items above the target price
                    if (orderItem.isActualAboveTarget()) {
                        int marginalRevenue = orderItem.calculatePricePerformance();
                        totalMarginalRevenue += marginalRevenue;
                    }
                }
            }

            // Update the aggregated marginal revenue for the market
            marginalRevenuesByMarket.put(market, totalMarginalRevenue);
        }

        // Display the total marginal revenue broken down by market
        System.out.print("================================");
        System.out.print("Total Marginal Revenue Broken Down by Market:");
        System.out.println("================================");
        for (Map.Entry<Market, Integer> entry : marginalRevenuesByMarket.entrySet()) {
            Market market = entry.getKey();
            int totalMarginalRevenue = entry.getValue();

            // Display information for each market
            System.out.println("Market: " + market.toString());
            System.out.println("Total Marginal Revenue: $" + totalMarginalRevenue);
            System.out.println();
        }
    }

    public void displayMostSellingPricesForAllSolutionOffers() {
        // Get the list of solution offers
        List<SolutionOffer> solutionOffers = business.getSolutionOfferCatalog().getSolutionoffers();

        // Display header for the table
        System.out.println("=====================================================================================================");
        System.out.println("Solution Offer\t\tTarget Price\t\tMost Selling Price Below Target\t\tMost Selling Price Above Target");
        System.out.println("=====================================================================================================");

        // Iterate through each solution offer
        for (SolutionOffer solutionOffer : solutionOffers) {
            // Find most selling prices
            String mostSellingPriceBelowTarget = findMostSellingPriceBelowTarget(solutionOffer);
            String mostSellingPriceAboveTarget = findMostSellingPriceAboveTarget(solutionOffer);
            double targetPrice = solutionOffer.getTargetPrice();

            // Display information in tabular format
//            System.out.printf("%-20s\t\t$%-20.2f\t\t$%-30s\t\t$%-30s%n",
//                    solutionOffer, targetPrice,
//                    Double.isNaN(mostSellingPriceBelowTarget) ? "N/A" : mostSellingPriceBelowTarget,
//                    Double.isNaN(mostSellingPriceAboveTarget) ? "N/A" : mostSellingPriceAboveTarget);
            System.out.printf("%-20s\t\t$%-20.2f\t\t$%-30s\t\t$%-30s%n",
                    "x", targetPrice,
                    mostSellingPriceBelowTarget.contains("0.0") ? "N/A" : mostSellingPriceBelowTarget,
                    mostSellingPriceAboveTarget.contains("0.0") ? "N/A" : mostSellingPriceAboveTarget);
        }

        // Display footer for the table
        System.out.println("=====================================================================================================");
    }

    public String findMostSellingPriceBelowTarget(SolutionOffer solutionOffer) {
        double targetPrice = solutionOffer.getTargetPrice();
        // Filter solution orders for the specific solution offering and prices below the target
        List<Double> pricesBelowTarget = business.getMasterSolutionOrderList().getSolutionorderlist().stream()
                .flatMap(order -> order.getSolutionOrderItems().stream()) // Flatten to SolutionOrderItem stream
                .filter(orderItem
                        -> orderItem.isActualBelowTarget()
                && orderItem.getSelectedSolutionOffer() == solutionOffer)
                .map(solutionOrderItem -> solutionOrderItem.getActualPrice())
                .map(Double::valueOf)
                .collect(Collectors.toList());

        // Analyze the distribution and find the most common price
        String mostSellingPriceBelowTarget = calculateMostCommonPrice(pricesBelowTarget);

        return new StringBuilder(mostSellingPriceBelowTarget + ", Size: " + pricesBelowTarget.size()).toString();
    }

    public String findMostSellingPriceAboveTarget(SolutionOffer solutionOffer) {
        double targetPrice = solutionOffer.getTargetPrice();

        List<Double> pricesAboveTarget = business.getMasterSolutionOrderList().getSolutionorderlist().stream()
                .flatMap(order -> order.getSolutionOrderItems().stream())
                .filter(orderItem
                        -> orderItem.isActualAboveTarget()
                && orderItem.getSelectedSolutionOffer() == solutionOffer)
                .map(solutionOrderItem -> solutionOrderItem.getActualPrice())
                .map(Double::valueOf)
                .collect(Collectors.toList());

        // Analyze the distribution and find the most common price
        String mostSellingPriceAboveTarget = calculateMostCommonPrice(pricesAboveTarget);

        return new StringBuilder(mostSellingPriceAboveTarget + ", Size: " + pricesAboveTarget.size()).toString();
    }

    private String calculateMostCommonPrice(List<Double> prices) {
        // Implement logic to calculate the most common price (e.g., using a histogram or statistical analysis)
        // For simplicity, this example returns the average price.
        return String.format("%.2f", prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
    }

    public void displayLossBySolutionOffering() {
        // Get the list of markets
        List<Market> markets = business.getMarketCatalog().getMarkets();

        // Iterate through each market
        for (Market market : markets) {
            // Map to store aggregated losses for each solution offer in the current market
            Map<SolutionOffer, Integer> solutionOfferLosses = new HashMap<>();

            // Filter solution orders for the current market
            List<SolutionOrder> marketSolutionOrders = getSolutionOrdersForMarket(market);

            // Aggregate losses for each solution offer in the current market
            for (SolutionOrder solutionOrder : marketSolutionOrders) {
                for (SolutionOrderItem orderItem : solutionOrder.getSolutionOrderItems()) {
                    SolutionOffer solutionOffer = orderItem.getSelectedSolutionOffer();
                    int loss = orderItem.getSolutionOrderItemLoss();

                    // Only consider solutions selling below target price
                    if (loss > 0) {
                        // Update the aggregated loss for the solution offer
                        solutionOfferLosses.put(solutionOffer, solutionOfferLosses.getOrDefault(solutionOffer, 0) + loss);
                    }
                }
            }

            // Sort solution offers by loss in descending order
            List<Map.Entry<SolutionOffer, Integer>> sortedSolutionOffers = new ArrayList<>(solutionOfferLosses.entrySet());
            sortedSolutionOffers.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

            // Display the losses for each solution offering in the current market
            System.out.print("================================");
            System.out.print("Losses by Solution Offering for Market: " + market.toString());
            System.out.println("===============================");
            for (Map.Entry<SolutionOffer, Integer> entry : sortedSolutionOffers) {
                SolutionOffer solutionOffer = entry.getKey();
                int loss = entry.getValue();

                // Display information (customize the output based on your needs)
                System.out.println("Solution Offer: " + solutionOffer.toString());
                System.out.println("Loss: $" + loss);
                System.out.println("------------------------------");
                System.out.println();
            }
        }
    }

    public void Top3CustomersByMarket() {
        // Get the list of markets
        List<Market> markets = business.getMarketCatalog().getMarkets();

        // Iterate through each market
        for (Market market : markets) {
            // Filter solution orders for the current market
            List<SolutionOrder> marketSolutionOrders = getSolutionOrdersForMarket(market);

            // Map to store aggregated revenues for each customer in the market
            Map<CustomerProfile, Integer> customerRevenues = new HashMap<>();

            // Aggregate revenues for each solution order in the current market
            for (SolutionOrder solutionOrder : marketSolutionOrders) {
                // Only consider solution orders above target price
                if (solutionOrder.isSolutionOrderAboveTotalTarget()) {
                    // Get the customer associated with the solution order
                    CustomerProfile customer = solutionOrder.getCustomerProfile();

                    // Update the aggregated revenue for the customer in the market
                    customerRevenues.merge(customer, solutionOrder.getSolutionOrderTotal(), Integer::sum);
                }
            }

            // Sort customers by revenue in descending order
            List<Map.Entry<CustomerProfile, Integer>> sortedCustomers = new ArrayList<>(customerRevenues.entrySet());
            sortedCustomers.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

            // Display the top 3 customers for the current market
            int count = 0;
            System.out.print("================================");
            System.out.print("Top 3 Customers in Market " + market + " with Solution Orders Above Target:");
            System.out.println("================================");
            for (Map.Entry<CustomerProfile, Integer> entry : sortedCustomers) {
                if (count >= 3) {
                    break; // Stop after displaying the top 3
                }

                CustomerProfile customer = entry.getKey();
                int totalRevenue = entry.getValue();

                // Display information for each customer in the market
                System.out.println("Customer: " + customer.toString());
                System.out.println("Total Revenue: $" + totalRevenue);
                System.out.println();

                count++;
            }
        }
    }

}
