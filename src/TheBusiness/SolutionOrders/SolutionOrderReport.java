/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.SolutionOrders;
import java.util.ArrayList;

/**
 *
 * @author tirdesh
 */

public class SolutionOrderReport {

    ArrayList<SolutionOrderSummary> solutionOrderSummaryList;

    public SolutionOrderReport() {
        solutionOrderSummaryList = new ArrayList<>();
    }

    /**
     * Add a solution order summary to the report.
     * 
     * @param sos The solution order summary to be added.
     */
    public void addSolutionOrderSummary(SolutionOrderSummary sos) {
        solutionOrderSummaryList.add(sos);
    }

    /**
     * Get the top solution order above target based on a specific criterion.
     * 
     * @return The top solution order summary above target.
     */
    public SolutionOrderSummary getTopSolutionOrderAboveTarget() {
        SolutionOrderSummary currentTopSolutionOrder = null;

        for (SolutionOrderSummary sos : solutionOrderSummaryList) {
            if (currentTopSolutionOrder == null) {
                currentTopSolutionOrder = sos; // initial step 
            } else if (sos.getNumberOfSolutionOrderItemsAboveTarget()> currentTopSolutionOrder.getNumberOfSolutionOrderItemsAboveTarget()) {
                currentTopSolutionOrder = sos; // we have a new higher total
            }
        }
        return currentTopSolutionOrder;
    }

    /**
     * Get a list of solution orders that always meet or exceed the target.
     * 
     * @return The list of solution orders always above target.
     */
    public ArrayList<SolutionOrderSummary> getSolutionOrdersAlwaysAboveTarget() {
        ArrayList<SolutionOrderSummary> ordersAlwaysAboveTarget = new ArrayList<>();

        for (SolutionOrderSummary sos : solutionOrderSummaryList) {
            if (sos.isTotalAboveTarget()) {
                ordersAlwaysAboveTarget.add(sos);
            }
        }

        return ordersAlwaysAboveTarget;
    }
}
