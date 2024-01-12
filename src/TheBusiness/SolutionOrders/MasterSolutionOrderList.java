/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class MasterSolutionOrderList {
        ArrayList<SolutionOrder> solutionorderlist;

    public MasterSolutionOrderList() {
        solutionorderlist = new ArrayList();
    }

    public ArrayList<SolutionOrder> getSolutionorderlist() {
        return solutionorderlist;
    }

    public SolutionOrder newSolutionOrder(SolutionOffer soloffer,  MarketChannelAssignment mca) {

        SolutionOrder so = new SolutionOrder(soloffer, mca);
        solutionorderlist.add(so);
        return so;

    }
    public SolutionOrder newSolutionOrder(MarketChannelAssignment mca){
        SolutionOrder so = new SolutionOrder(mca);
        solutionorderlist.add(so);
        
        return so;
    }
    public SolutionOrder newSolutionOrder(SolutionOffer soloffer, MarketChannelAssignment mca, CustomerProfile customer, SalesPersonProfile salesperson) {

        SolutionOrder so = new SolutionOrder(soloffer, mca, customer, salesperson);
        solutionorderlist.add(so);
        return so;

    }

    public int getRevenueByMarket(Market m) {
        int sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getMarket()==m) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public int getRevenueByChannel(Channel c) {
        int sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getChannel()==c) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public int getRevenueByMarketChannelCombo(MarketChannelAssignment mca) {
        int sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc==mca) sum = sum +so.getSolutionPrice(); 
           
        }
        return sum;

    }
    
   public int getTotalRevenue() {
        int sum = 0;
        for (SolutionOrder so : solutionorderlist) {
            sum += so.getSolutionPrice();
        }
        return sum;
    }

    // New method to get the most profitable solution order
    public SolutionOrder getTopProfitableSolutionOrder() {
        SolutionOrder currentTopOrder = null;

        for (SolutionOrder so : solutionorderlist) {
            if (currentTopOrder == null) {
                currentTopOrder = so; // Initial step
            } else if (so.getSolutionPrice() > currentTopOrder.getSolutionPrice()) {
                currentTopOrder = so; // We have a new higher total
            }
        }
        return currentTopOrder;
    }
    
    public SolutionOrderReport generateSolutionOrderPerformanceReport() {
        SolutionOrderReport solutionOrderReport = new SolutionOrderReport();

        for (SolutionOrder solutionOrder : solutionorderlist) {
            SolutionOrderSummary sos = new SolutionOrderSummary(solutionOrder);
            solutionOrderReport.addSolutionOrderSummary(sos);
        }

        return solutionOrderReport;
    }

}
