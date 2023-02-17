import enums.StockType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * All the requirement
 *
 * @author Manoj Kumar created on 17-02-2023
 */
public class AllOperations {
    private static Map<String, Stock> sampleDataFromGBCE() {

        HashMap<String, Stock> map = new HashMap<>();
        map.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        map.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        map.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        map.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        map.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        return map;
    }

    public static void operation() {
        try {
            Map<String, Stock> sampleData = sampleDataFromGBCE();
            for (Stock stock: sampleData.values()) {
                System.out.println( stock.getSymbol() + " dividend: " + stock.dividend(9.1));
                System.out.println( stock.getSymbol() + " P/E Ratio: " + stock.PERatio(9.1));
                for (int i=1; i <= 10; i++) {
                    Random r = new Random();
                    int rangeMin = 1;
                    int rangeMax = 10;
                    double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
                    stock.buy(i, randomValue);
                    System.out.println( stock.getSymbol() + " bought " + i + " shares at $" + randomValue);
                    randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
                    stock.sell(i, randomValue);
                    System.out.println( stock.getSymbol() + " sold " + i + " shares at $" + randomValue);
                    Thread.sleep(1000);
                }
                System.out.println( stock.getSymbol() + " price: $" + stock.getPrice());
                System.out.println( stock.getSymbol() + " volumeWeightedStockPrice: $" + stock.calculateVolumeWeightedStockPrice());
            }
            Double GBCEallShareIndex = GlobalBeverageCorporationExchange.allShareIndex(sampleData);
            System.out.println( "GBCE All Share Index: " + GBCEallShareIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
