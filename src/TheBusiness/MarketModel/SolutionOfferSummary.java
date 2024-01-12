/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.MarketModel;

import TheBusiness.SolutionOrders.SolutionOrderItem;

/**
 *
 * @author tirdesh
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SolutionOfferSummary {

    SolutionOffer subjectSolutionOffer;
    int numberofSalesAboveTarget;
    int numberofSalesBelowTarget;
    int solutionOfferPricePerformance; // Total profit above target -- could be negative too
    int actualSalesVolume;
    int rank; // Will be done later

    public SolutionOfferSummary(SolutionOffer so) {
        subjectSolutionOffer = so; // Keeps track of the solution offer itself
        numberofSalesAboveTarget = so.getNumberOfSolutionSalesAboveTarget();
        solutionOfferPricePerformance = so.getSolutionOrderPricePerformance();
        actualSalesVolume = so.getRevenues();
        numberofSalesBelowTarget = so.getNumberOfSolutionSalesBelowTarget();
    }

    public int getSalesRevenues() {
        return actualSalesVolume;
    }

    public int getNumberAboveTarget() {
        return numberofSalesAboveTarget;
    }

    public int getSolutionOfferPricePerformance() {
        return solutionOfferPricePerformance;
    }

    public int getNumberBelowTarget() {
        return numberofSalesBelowTarget;
    }

    public boolean isSolutionOfferAlwaysAboveTarget() {
        for (SolutionOrderItem soi : subjectSolutionOffer.solutionorderitems) {
            if (!soi.isActualAboveTarget()) {
                return false; // If any solution order item is not above target, return false
            }
        }
        return true; // All solution order items are above target
    }
}
