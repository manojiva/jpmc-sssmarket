import java.util.Map;

/**
 * Manage global beverage corporation exchange operations
 *
 * @author Manoj Kumar created on 17-02-2023
 */
public class GlobalBeverageCorporationExchange {

    /**
     * Calculate the global beverage corporation exchange all share index for all stocks
     *
     * @param stocks
     * @return The global beverage corporation exchange all share index
     */
    public static Double allShareIndex(Map<String, Stock> stocks) {
        Double allShareIndex = 0.0;
        for(Stock stock: stocks.values()) {
            allShareIndex+=stock.getPrice();
        }
        return Math.pow(allShareIndex, 1.0 / stocks.size());
    }
}
