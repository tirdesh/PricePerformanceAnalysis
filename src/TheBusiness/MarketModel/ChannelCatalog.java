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
public class ChannelCatalog {
    ArrayList<Channel> channellist ;
    public ChannelCatalog(){
    channellist = new ArrayList();
    }
    
    public Channel newChannel(String type){
        
        Channel c = new Channel(type);
        channellist.add(c);
        return c;
    }
    
    public Channel findChannel(String type){
        
        for( Channel c: channellist ){
            
            if (c.getChannelType().equalsIgnoreCase(type)) return c;
            
        }
        return null; //not found
    }
    
    
}
