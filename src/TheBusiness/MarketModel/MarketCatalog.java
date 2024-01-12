/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.MarketModel;

import TheBusiness.MarketModel.Market;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class MarketCatalog {

    ArrayList<Market> markets;
    // constructor  MarketCatalog 

    public MarketCatalog() {

        markets = new ArrayList();

    }

    public Market newMarket(String m) {

        Market market = new Market(m);
        markets.add(market);
        return market;
    }
    
    public Market findMarket(String id) {
        for (Market m : markets) {

            if (m.isMatch(id)) {
                return m;
            }
        }
            return null; //not found after going through the whole list
    }
    
    public ArrayList<Market> getMarkets(){
        return markets;
    }
    
}
