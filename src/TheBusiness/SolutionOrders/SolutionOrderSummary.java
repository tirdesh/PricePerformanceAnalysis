/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.SolutionOrders;

/**
 *
 * @author tirdesh
 */
public class SolutionOrderSummary {

    private SolutionOrder subjectSolutionOrder;
    private int salesVolume;
    private boolean totalAboveTarget;
    private int solutionOrderPricePerformance;
    private int numberOfSolutionOrderItemsAboveTarget;
    private int numberOfSolutionOrderItemsBelowTarget;
    // Add any other fields as needed

    public int getSalesRevenues() {
        return salesVolume;
    }

    public boolean isTotalAboveTarget() {
        return totalAboveTarget;
    }

    public int getSolutionOrderPricePerformance() {
        return solutionOrderPricePerformance;
    }

    public int getNumberOfSolutionOrderItemsAboveTarget() {
        return numberOfSolutionOrderItemsAboveTarget;
    }

    public int getNumberOfSolutionOrderItemsBelowTarget() {
        return numberOfSolutionOrderItemsBelowTarget;
    }

    public SolutionOrderSummary(SolutionOrder solutionOrder) {
        subjectSolutionOrder = solutionOrder;
        salesVolume = solutionOrder.getSalesVolume();
        totalAboveTarget = solutionOrder.isSolutionOrderAboveTotalTarget();
        solutionOrderPricePerformance = solutionOrder.getSolutionOrderPricePerformance();
        numberOfSolutionOrderItemsAboveTarget = solutionOrder.getNumberOfSolutionOrderItemsAboveTarget();
        numberOfSolutionOrderItemsBelowTarget = solutionOrder.getNumberOfSolutionOrderItemsBelowTarget();
        // Add initialization for other fields
    }

    public SolutionOrder getSubjectSolutionOrder() {
        return subjectSolutionOrder;
    }

    public int getSolutionOrderProfit() {
        return solutionOrderPricePerformance;
    }
    
    public String feedback1() {
        return totalAboveTarget ?
                "Sales are above target. Encourage the customer to maintain or increase the order." :
                "Sales are below target. Identify opportunities to upsell or cross-sell.";
    }

    public String feedback2() {
        return solutionOrderPricePerformance > 0 ?
                "Positive solution order price performance. Highlight the value proposition." :
                "Negative solution order price performance. Offer discounts or additional incentives.";
    }

    public void displaySummary() {
        System.out.println("Solution Order Summary:");
        System.out.println("Subject Solution Order: " + subjectSolutionOrder);
        System.out.println("Sales Volume: " + salesVolume);
        System.out.println("Total Above Target: " + totalAboveTarget);
        System.out.println("Solution Order Price Performance: " + solutionOrderPricePerformance);
        System.out.println("Number of Solution Order Items Above Target: " + numberOfSolutionOrderItemsAboveTarget);
        // Add display for other fields as needed
    }
}
