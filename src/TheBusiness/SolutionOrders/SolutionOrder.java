/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;

public class SolutionOrder {

    private ArrayList<SolutionOffer> solutionOffers = new ArrayList<>();
    private MarketChannelAssignment marketChannelAssignment;
    private CustomerProfile customerProfile;
    private SalesPersonProfile salesPersonProfile;
    private String status;
    private ArrayList<SolutionOrderItem> solutionOrderItems = new ArrayList<>();

    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }

   public void AddSolutionOrder(SolutionOffer s){
       solutionOffers.add(s);
   }
   

    public MarketChannelAssignment getMarketChannelAssignment() {
        return marketChannelAssignment;
    }

    public ArrayList<SolutionOrderItem> getSolutionOrderItems() {
        return solutionOrderItems;
    }

    public SolutionOrder(SolutionOffer so, MarketChannelAssignment mca, CustomerProfile customer, SalesPersonProfile salesperson) {
        solutionOffers.add(so);
        marketChannelAssignment = mca;
        customerProfile = customer;
        salesPersonProfile = salesperson;
        status = "in process";
        this.solutionOrderItems = new ArrayList<>();
    }    
    public SolutionOrder(MarketChannelAssignment mca){
        marketChannelAssignment = mca;
    }
    public SolutionOrderItem newSolutionOrderItem(SolutionOffer solutionOffer, int paidPrice, int q){
        SolutionOrderItem so= new SolutionOrderItem(solutionOffer, paidPrice, q);
        solutionOrderItems.add(so);
        return so;
    }
        
    public SolutionOrder(SolutionOffer so, MarketChannelAssignment mca) {
        solutionOffers.add(so);
        marketChannelAssignment = mca;
        status = "in process";
    }
    
    public void addSolutionOrderItem(SolutionOffer solutionOffer, int paidPrice, int quantity) {
        SolutionOrderItem solutionOrderItem = new SolutionOrderItem(solutionOffer, paidPrice, quantity);
        solutionOrderItems.add(solutionOrderItem);
    }

    public int getSolutionOrderTotal() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            sum += solutionOrderItem.getSolutionOrderItemTotal();
        }
        return sum;
    }

    public int getSolutionOrderPricePerformance() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            sum += solutionOrderItem.calculatePricePerformance();
        }
        return sum;
    }

    public int getNumberOfSolutionOrderItemsAboveTarget() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            if (solutionOrderItem.isActualAboveTarget()) {
                sum += 1;
            }
        }
        return sum;
    }

    public boolean isSolutionOrderAboveTotalTarget() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            sum += solutionOrderItem.getSolutionOrderItemTargetTotal();
        }
        return getSolutionOrderTotal() > sum;
    }

    public int getSalesVolume() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            sum = sum + solutionOrderItem.getSolutionOrderItemTotal();     //positive and negative values       
        }
        return sum;
    }

    public int getSolutionPrice() {
        int sum=0;
        for(SolutionOffer so : solutionOffers){
        sum=sum+so.getSolutionPrice();
    }
        return sum;
    }

    public MarketChannelAssignment getMarketChannelCombo() {
        return marketChannelAssignment;
    }

    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public SalesPersonProfile getSalesPersonProfile() {
        return salesPersonProfile;
    }

    public String getStatus() {
        return status;
    }

    public void cancelSolutionOrder() {
        status = "Cancelled";
    }

    public void submitSolutionOrder() {
        status = "Submitted";
    }

    public int getSolutionOrderTargetTotal() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            sum += solutionOrderItem.getSolutionOrderItemTargetTotal();
        }
        return sum;
    }

    int getNumberOfSolutionOrderItemsBelowTarget() {
        int sum = 0;
        for (SolutionOrderItem solutionOrderItem : solutionOrderItems) {
            if (solutionOrderItem.isActualBelowTarget()) {
                sum += 1;
            }
        }
        return sum;
    }

}

