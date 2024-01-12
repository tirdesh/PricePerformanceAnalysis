/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.MarketModel;

import java.util.ArrayList;

/**
 *
 * @author tirdesh
 */
public class SolutionOfferReport {

    ArrayList<SolutionOfferSummary> solutionOfferSummaryList;

    public SolutionOfferReport() {
        solutionOfferSummaryList = new ArrayList<>();
    }

    public void addSolutionOfferSummary(SolutionOfferSummary sos) {
        solutionOfferSummaryList.add(sos);
    }

    public SolutionOfferSummary getTopSolutionOfferAboveTarget() {
        SolutionOfferSummary currentTopSolutionOffer = null;

        for (SolutionOfferSummary sos : solutionOfferSummaryList) {
            if (currentTopSolutionOffer == null) {
                currentTopSolutionOffer = sos; // initial step 
            } else if (sos.isSolutionOfferAlwaysAboveTarget() && 
                       sos.getNumberAboveTarget() > currentTopSolutionOffer.getNumberAboveTarget()) {
                currentTopSolutionOffer = sos; // we have a new higher total
            }
        }

        return currentTopSolutionOffer;
    }

    public ArrayList<SolutionOfferSummary> getSolutionOffersAlwaysAboveTarget() {
        ArrayList<SolutionOfferSummary> solutionOffersAlwaysAboveTarget = new ArrayList<>();

        for (SolutionOfferSummary sos : solutionOfferSummaryList) {
            if (sos.isSolutionOfferAlwaysAboveTarget()) {
                solutionOffersAlwaysAboveTarget.add(sos);
            }
        }

        return solutionOffersAlwaysAboveTarget;
    }
}
