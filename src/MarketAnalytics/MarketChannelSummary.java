/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MarketAnalytics;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.MarketChannelAssignment;
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
 * @author kal bugrara
 */
public class MarketChannelSummary {

    Business business;
    MarketChannelAssignment marketchannelassignment;
    int revenues;

    public MarketChannelSummary(Business b, MarketChannelAssignment mc) {
        business = b;
        marketchannelassignment = mc;
        revenues = mc.getRevenues();
    }

    public int getRevenues() {
        return revenues;
    }

    public List<Map.Entry<SolutionOffer, Integer>> top3NegotiatedSolutionsForMCA(MarketChannelAssignment mca) {
        // Map to store aggregated revenues for each solution offer in the given MCA
        Map<SolutionOffer, Integer> solutionOfferRevenues = new HashMap<>();

        // Filter solution orders for the given MCA
        List<SolutionOrder> mcaSolutionOrders = getSolutionOrdersForMCA(mca);

        // Aggregate revenues for each solution offer in the given MCA
        for (SolutionOrder solutionOrder : mcaSolutionOrders) {
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

        // Get the top 3 negotiated solutions
        List<Map.Entry<SolutionOffer, Integer>> top3SolutionOffers = sortedSolutionOffers.stream()
                .limit(3)
                .collect(Collectors.toList());

        return top3SolutionOffers;
    }

    // Helper method to get solution orders associated with a specific MCA
    private List<SolutionOrder> getSolutionOrdersForMCA(MarketChannelAssignment mca) {
        List<SolutionOrder> mcaSolutionOrders = new ArrayList<>();
        for (SolutionOrder solutionOrder : business.getMasterSolutionOrderList().getSolutionorderlist()) {
            if (solutionOrder.getMarketChannelCombo() == mca) {
                mcaSolutionOrders.add(solutionOrder);
            }
        }
        return mcaSolutionOrders;
    }

    public List<Map.Entry<CustomerProfile, Integer>> top3CustomersByMCA(MarketChannelAssignment mca) {
        // Filter solution orders for the given MCA
        List<SolutionOrder> mcaSolutionOrders = getSolutionOrdersForMCA(mca);

        // Map to store aggregated revenues for each customer in the MCA
        Map<CustomerProfile, Integer> customerRevenues = new HashMap<>();

        // Aggregate revenues for each solution order in the given MCA
        for (SolutionOrder solutionOrder : mcaSolutionOrders) {
            // Only consider solution orders above target price
            if (solutionOrder.isSolutionOrderAboveTotalTarget()) {
                // Get the customer associated with the solution order
                CustomerProfile customer = solutionOrder.getCustomerProfile();

                // Update the aggregated revenue for the customer in the MCA
                customerRevenues.merge(customer, solutionOrder.getSolutionOrderTotal(), Integer::sum);
            }
        }

        // Sort customers by revenue in descending order
        List<Map.Entry<CustomerProfile, Integer>> sortedCustomers = new ArrayList<>(customerRevenues.entrySet());
        sortedCustomers.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Get the top 3 customers
        List<Map.Entry<CustomerProfile, Integer>> top3Customers = sortedCustomers.stream()
                .limit(3)
                .collect(Collectors.toList());

        return top3Customers;
    }

    public List<Map.Entry<SalesPersonProfile, Integer>> top3SalesPeopleByMCA(MarketChannelAssignment mca) {
        // Get the list of salespeople
        List<SalesPersonProfile> salespeople = business.getSalesPersonDirectory().getSalesPersonList();

        // Map to store aggregated revenues for each salesperson in the MCA
        Map<SalesPersonProfile, Integer> salespersonRevenues = new HashMap<>();

        // Iterate through each salesperson
        for (SalesPersonProfile salesperson : salespeople) {
            int totalRevenue = 0;

            // Filter solution orders for the current salesperson and MCA
            List<SolutionOrder> salespersonSolutionOrders = getSolutionOrdersForSalesPersonAndMCA(salesperson, mca);

            // Aggregate revenues for each solution order in the current salesperson and MCA
            for (SolutionOrder solutionOrder : salespersonSolutionOrders) {
                // Only consider solution orders above target price
                if (solutionOrder.isSolutionOrderAboveTotalTarget()) {
                    totalRevenue += solutionOrder.getSolutionOrderTotal();
                }
            }

            // Update the aggregated revenue for the salesperson in the MCA
            salespersonRevenues.put(salesperson, totalRevenue);
        }

        // Sort salespeople by revenue in descending order
        List<Map.Entry<SalesPersonProfile, Integer>> sortedSalespeople = new ArrayList<>(salespersonRevenues.entrySet());
        sortedSalespeople.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // Get the top 3 salespeople
        List<Map.Entry<SalesPersonProfile, Integer>> top3Salespeople = sortedSalespeople.stream()
                .limit(3)
                .collect(Collectors.toList());

        return top3Salespeople;
    }

    private List<SolutionOrder> getSolutionOrdersForSalesPersonAndMCA(SalesPersonProfile salesperson, MarketChannelAssignment mca) {
        List<SolutionOrder> salespersonSolutionOrders = new ArrayList<>();
        for (SolutionOrder solutionOrder : business.getMasterSolutionOrderList().getSolutionorderlist()) {
            if (solutionOrder.getMarketChannelCombo() == mca && solutionOrder.getSalesPersonProfile() == salesperson) {
                salespersonSolutionOrders.add(solutionOrder);
            }
        }
        return salespersonSolutionOrders;
    }

    public int getTotalSolutionOrdersForMCA(MarketChannelAssignment mca) {
        return getSolutionOrdersForMCA(mca).size();
    }

    public int getSolutionOffersAboveTargetCount(MarketChannelAssignment mca) {
        // Filter solution orders for the given MCA
        List<SolutionOrder> mcaSolutionOrders = getSolutionOrdersForMCA(mca);

        // Count the number of solution offers performing above target
        int aboveTargetCount = 0;

        // Iterate through each solution order
        for (SolutionOrder solutionOrder : mcaSolutionOrders) {
            // Iterate through each order item in the solution order
            for (SolutionOrderItem orderItem : solutionOrder.getSolutionOrderItems()) {
                SolutionOffer solutionOffer = orderItem.getSelectedSolutionOffer();
                int revenue = orderItem.getSolutionOrderItemTotal();

                // Check if the revenue is above the target price
                if (revenue > solutionOffer.getTargetPrice() * orderItem.getQuantity()) {
                    aboveTargetCount++;
                }
            }
        }

        return aboveTargetCount;
    }

    public int getSolutionOffersBelowTargetCount(MarketChannelAssignment mca) {
        // Filter solution orders for the given MCA
        List<SolutionOrder> mcaSolutionOrders = getSolutionOrdersForMCA(mca);

        // Count the number of solution offers performing below target
        int belowTargetCount = 0;

        // Iterate through each solution order
        for (SolutionOrder solutionOrder : mcaSolutionOrders) {
            // Iterate through each order item in the solution order
            for (SolutionOrderItem orderItem : solutionOrder.getSolutionOrderItems()) {
                SolutionOffer solutionOffer = orderItem.getSelectedSolutionOffer();
                int revenue = orderItem.getSolutionOrderItemTotal();

                // Check if the revenue is below the target price
                if (revenue < solutionOffer.getTargetPrice() * orderItem.getQuantity()) {
                    belowTargetCount++;
                }
            }
        }

        return belowTargetCount;
    }

}
