/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.MarketModel;

import java.util.ArrayList;
import TheBusiness.ProductManagement.Product;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.SolutionOrders.SolutionOrderItem;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    ArrayList<Product> products;
    int price;//floor, ceiling, and target ideas
    private int floorPrice = 0;
    private int ceilingPrice = 0;
    private int targetPrice = 0;
    String ad;
    MarketChannelAssignment marketchannelcomb;
    ArrayList<SolutionOrderItem> solutionorderitems;

    public MarketChannelAssignment getMarketchannelcomb() {
        return marketchannelcomb;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    
    public SolutionOffer(MarketChannelAssignment m){
        marketchannelcomb = m;
        products = new ArrayList();
        solutionorderitems = new ArrayList();
        m.addSolutionOffer(this); 
       
    } 

    public int getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(int floorPrice) {
        this.floorPrice = floorPrice;
    }

    public int getCeilingPrice() {
        return ceilingPrice;
    }

    public void setCeilingPrice(int ceilingPrice) {
        this.ceilingPrice = ceilingPrice;
    }

    public int getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(int targetPrice) {
        this.targetPrice = targetPrice;
    }
    
    public void addProduct(Product p){
        products.add(p);
        floorPrice += p.getFloorPrice();
        ceilingPrice += p.getCeilingPrice();
        targetPrice += p.getTargetPrice();
    }
    public void setTotalPrice(int p){
        price = p;

    }
    public int getSolutionPrice(){
        return price;
    }
    
    public int getRevenues(){
        int sum = 0;
        for(SolutionOrderItem soi: solutionorderitems){
            sum = sum + soi.getSolutionOrderItemTotal();
            
        }
        return sum;
    }
    
    public void addSolutionOrderItem(SolutionOrderItem soi){
        solutionorderitems.add(soi);
    }
    // this will allow one to retrieve all offers meant for this market/channel combo
    public boolean isSolutionOfferMatchMarketChannel(MarketChannelAssignment mca){
        
        if (marketchannelcomb==mca) return true;
        else return false;
    }
    public String getAd(){
        return ad;
    }
    public void setAd(String a){ //this an amazing solution for people like
        ad = a;
    }
    
    public void displayPrices(){
        System.out.println("Floor Price:"+ floorPrice);
        System.out.println("Ceiling Price"+ ceilingPrice);
        System.out.println("Target Price"+ targetPrice);
    }
    
    public int getNumberOfSolutionSalesAboveTarget() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionorderitems) {
            if (soi.isActualAboveTarget()) {
                sum++;
            }
        }
        return sum;
    }

    public int getNumberOfSolutionSalesBelowTarget() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionorderitems) {
            if (soi.isActualBelowTarget()) {
                sum++;
            }
        }
        return sum;
    }

    public boolean isSolutionAlwaysAboveTarget() {
        for (SolutionOrderItem soi : solutionorderitems) {
            if (!soi.isActualAboveTarget()) {
                return false;
            }
        }
        return true;
    }

    public int getSolutionOrderPricePerformance() {
        int sum = 0;
        for (SolutionOrderItem soi : solutionorderitems) {
            sum += soi.calculatePricePerformance();
        }
        return sum;
    }
    
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        // Append the list of products
        stringBuilder.append("Products: [");
        for (Product product : products) {
            stringBuilder.append(product.toString()).append(", ");
        }
        // Remove the trailing comma and space if there are products
        if (!products.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();    
    }
    
}
