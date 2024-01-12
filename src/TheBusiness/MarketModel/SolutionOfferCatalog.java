/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.MarketModel;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {

    ArrayList<SolutionOffer> solutionoffers;

    public ArrayList<SolutionOffer> getSolutionoffers() {
        return solutionoffers;
    }

    public SolutionOfferCatalog() {
        solutionoffers = new ArrayList();
    }

    public SolutionOffer newSolutionOffer(MarketChannelAssignment mca) {

        SolutionOffer so = new SolutionOffer(mca);
        solutionoffers.add(so);

        return so;
    }

    public SolutionOfferReport generateSolutionOfferPerformanceReport() {
        SolutionOfferReport solutionOffersReport = new SolutionOfferReport();

        for (SolutionOffer so : solutionoffers) {
            SolutionOfferSummary sos = new SolutionOfferSummary(so);
            solutionOffersReport.addSolutionOfferSummary(sos);
        }

        return solutionOffersReport;
    }

    //return all solution offers that match m/c combination
    public ArrayList<SolutionOffer> findSolutionsForMarketChannelCombo(MarketChannelAssignment mcc) {
        ArrayList<SolutionOffer> foundsolutions = new ArrayList();

        for (SolutionOffer so : solutionoffers) {

            if (so.isSolutionOfferMatchMarketChannel(mcc) == true) {
                foundsolutions.add(so);
            }
            //find all solution offers available in the market/channel combin
        }
        return foundsolutions;

    }

}
