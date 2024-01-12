/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.MarketModel;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author kal bugrara
 */
public class MarketChannelComboCatalog {
    
    ArrayList<MarketChannelAssignment> mcalist ;
    
   public MarketChannelComboCatalog() {
       
       mcalist = new ArrayList();
       
   }

    public ArrayList<MarketChannelAssignment> getMcalist() {
        return mcalist;
    }
   
   public MarketChannelAssignment newMarketChannelCombo(Market m, Channel c){
       MarketChannelAssignment mcc = new MarketChannelAssignment(m, c);
       mcalist.add(mcc);
       return mcc;
       
   }
   public MarketChannelAssignment finMarketChannelCombo(Market m, Channel c){
       
       for( MarketChannelAssignment mca: mcalist){
           if(mca.matches(m,c)) return mca;
       }
       return null;
       
   }
   
    public MarketChannelAssignment getRandomMarketChannelCombo() {
        if (!mcalist.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(mcalist.size());
            return mcalist.get(randomIndex);
        } else {
            return null; // Return null if the list is empty
        }
    }
    
    public MarketChannelAssignment getRandomMarketChannelCombo(Market m) {
        ArrayList<MarketChannelAssignment> matchingAssignments = new ArrayList<>();

        for (MarketChannelAssignment mca : mcalist) {
            if (mca.getMarket() == m && mca.getSolutionofferlist() != null && !mca.getSolutionofferlist().isEmpty()) {
                matchingAssignments.add(mca);
            }
        }

        if (!matchingAssignments.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(matchingAssignments.size());
            return matchingAssignments.get(randomIndex);
        } else {
            return null; // Return null if there are no matching assignments for the given market
        }
    }
//
//    public MarketChannelAssignment finMarketChannelCombo(String marketname) {
//    return finMarketChannelCombo(marketname.split("_")[0],marketname.split("_")[1]);
//    
//            }
   
}
