/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.SolutionOrders;

/**
 *
 * @author tirdesh
 */

import TheBusiness.MarketModel.SolutionOffer;

public class SolutionOrderItem {

    private SolutionOffer selectedSolutionOffer;
    private int actualPrice;
    private int quantity;

    public  SolutionOrderItem(SolutionOffer solutionOffer, int paidPrice, int q) {
        selectedSolutionOffer = solutionOffer;
        quantity = q;
        this.actualPrice = paidPrice;
                selectedSolutionOffer.addSolutionOrderItem(this);
    }

    public int getSolutionOrderItemTotal() {
        return actualPrice * quantity;
    }

    // The following calculates what the price gain would have been if products were sold at the target price
    public int getSolutionOrderItemTargetTotal() {
        return selectedSolutionOffer.getTargetPrice() * quantity;
    }

    // Returns positive if the seller is making a higher margin than the target
    // Returns negative if the seller is making a lower margin than the target
    // Otherwise zero, meaning neutral
    public int calculatePricePerformance() {
        return (actualPrice - selectedSolutionOffer.getTargetPrice()) * quantity;
    }

    public boolean isActualAboveTarget() {
        return actualPrice > selectedSolutionOffer.getTargetPrice();
    }

    public boolean isActualBelowTarget() {
        return actualPrice < selectedSolutionOffer.getTargetPrice();
    }

    public boolean isActualAtTarget() {
        return actualPrice == selectedSolutionOffer.getTargetPrice();
    }

    public SolutionOffer getSelectedSolutionOffer() {
        return selectedSolutionOffer;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSolutionOrderItemLoss() {

        // Calculate the loss
        int loss = getSolutionOrderItemTargetTotal() - getSolutionOrderItemTotal();

        return loss;
    }
}
