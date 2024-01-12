/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.ProductManagement.Product;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tirdesh
 */

public class DataLoader {

    private static final String DATA_FOLDER = System.getProperty("user.dir")+ "/Data/";
    private static final List<String> FILENAMES = List.of(
            "Suppliers.csv", "Products.csv", "Customers.csv", "SalesPersons.csv",
            "Markets.csv", "Channels.csv", "MarketChannelMapping.csv"
    );
    
    
    private static Business business;  // Class variable to store the Business object

    public static void setBusiness(Business business) {
        DataLoader.business = business;
        String currentDirectory = System.getProperty("user.dir");
    }

    private static List<String[]> readCSV(String filename) {
        filename = DATA_FOLDER + filename;
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    
    
    public static void loadData() {

        FILENAMES.forEach(filename -> {
            if (filename.endsWith(".csv")) {
                String dataType = filename.replace(".csv", "");
                try {
                Method method = DataLoader.class.getDeclaredMethod("load"+ dataType);
                method.invoke(DataLoader.class, (Object[]) null);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                    Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Invalid file format: " + filename);
            }
        });
    }
    
    public static void loadProducts() {
        List<String[]> productData = readCSV("Products.csv");

        for (String[] data : productData) {
            
            String productName = data[0];
            int basePrice = Integer.parseInt(data[1]);
            int targetPrice = Integer.parseInt(data[2]);
            int ceilingPrice = Integer.parseInt(data[3]);

            Supplier randomSupplier = business.getSupplierDirectory().getRandomSupplier();

            // Add the product to the selected supplier's product catalog
            if (randomSupplier != null) {
                randomSupplier.getProductCatalog().newProduct(productName, basePrice, ceilingPrice, targetPrice);
            }
        }
    }

    public static void loadCustomers() {
        List<String[]> customerData = readCSV("Customers.csv");

        CustomerDirectory customerDirectory = business.getCustomerDirectory();
        PersonDirectory personDirectory = business.getPersonDirectory();

        for (String[] data : customerData) {
            String personName = data[0];
            Person person = personDirectory.findPerson(personName);

            if (person == null) {
                person = personDirectory.newPerson(personName);
            }
            
            customerDirectory.newCustomerProfile(person);
        }    
    }

    public static void loadSalesPersons() {
        List<String[]> fileData = readCSV("SalesPersons.csv");

        SalesPersonDirectory salesPersonDirectory = business.getSalesPersonDirectory();
        PersonDirectory personDirectory = business.getPersonDirectory();

        for (String[] data : fileData) {
            String personName = data[0];
            Person person = personDirectory.findPerson(personName);

            if (person == null) {
                person = personDirectory.newPerson(personName);
            }
            
            salesPersonDirectory.newSalesPersonProfile(person);
        } 
    }

    public static void loadMarkets() {
        List<String[]> fileData = readCSV("Markets.csv");

        MarketCatalog marketCatalog = business.getMarketCatalog();

        for (String[] data : fileData) {
            marketCatalog.newMarket(data[0]);
        } 
    }

    public static void loadChannels() {

        List<String[]> fileData = readCSV("Channels.csv");

        ChannelCatalog channelCatalog = business.getChannelCatalog();

        for (String[] data : fileData) {
            channelCatalog.newChannel(data[0]);
        } 
    }

    public static void loadSuppliers() {

        List<String[]> fileData = readCSV("Suppliers.csv");

        SupplierDirectory supplierDirectory = business.getSupplierDirectory();
        for (String[] data : fileData) {
            supplierDirectory.newSupplier(data[0]);
        } 
    }
        
    public static void loadMarketChannelMapping() {
        List<String[]> mappingData = readCSV("MarketChannelMapping.csv");

        for (String[] data : mappingData) {
            business.getMarketChannelComboCatalog().newMarketChannelCombo(
                    business.getMarketCatalog().findMarket(data[0]), 
                    business.getChannelCatalog().findChannel(data[1]));
        }
    }
    public static void loadSolutionOffers() {
        List<String[]> solutionOfferData = readCSV("SolutionOffers.csv");

        for (String[] data : solutionOfferData) {
            String marketChannelName = data[0];
            String[] productNames = data[1].split(", ");
            double price = 0;
            try {
                price = Double.parseDouble(data[2]);
                // Rest of the code...
            } catch (NumberFormatException e) {
                continue;
            }

            // Split marketChannelName to get market and channel names
            String[] marketChannelNames = marketChannelName.split("_");

            // Check if marketChannelNames has expected length
            if (marketChannelNames.length == 2) {
                String marketName = marketChannelNames[0];
                String channelName = marketChannelNames[1];

                // Find Market and Channel objects
                Market market = business.getMarketCatalog().findMarket(marketName);
                Channel channel = business.getChannelCatalog().findChannel(channelName);

                if (market == null) {
                    market = business.getMarketCatalog().newMarket(marketName);
                }
                if (channel == null) {
                    channel = business.getChannelCatalog().newChannel(channelName);
                }

                MarketChannelAssignment mca = business.getMarketChannelComboCatalog().finMarketChannelCombo(market, channel);
                if (mca == null) {
                    mca = business.getMarketChannelComboCatalog().newMarketChannelCombo(market, channel);
                }

                SolutionOffer solutionOffer = business.getSolutionOfferCatalog().newSolutionOffer(mca);
                mca.addSolutionOffer(solutionOffer);

                // Add products to SolutionOffer
                for (String productName : productNames) {
                    Supplier randSupplier = business.getSupplierDirectory().getRandomSupplier();
                    Product product = randSupplier.getProductCatalog().findProduct(productName);

                    if (product == null) {
                        randSupplier.getProductCatalog().getRandomProduct();
                    } 
                    solutionOffer.addProduct(product);
                }

                // Set the total price for SolutionOffer
                solutionOffer.setTotalPrice((int) price);
            }
        }
    }
    
}
