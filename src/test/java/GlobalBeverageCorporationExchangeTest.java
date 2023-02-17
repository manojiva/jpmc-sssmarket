import enums.StockType;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test class for unit test global beverage corporation exchange operations
 *
 * @author Manoj Kumar created on 17-02-2023
 */
public class GlobalBeverageCorporationExchangeTest {

    @Test
    public void testAllShareIndex() {
        HashMap<String, Stock> db = new HashMap<>();
        db.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        db.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        db.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        db.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        db.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
        for (Stock stock: db.values()) {
            for (int i=1; i <= 10; i++) {
                stock.buy(1, 1.0);
                stock.sell(1, 1.0);
            }
        }
        Double GBCEallShareIndex = GlobalBeverageCorporationExchange.allShareIndex(db);
        assertEquals(1.379729661461215, GBCEallShareIndex, 0.0);
    }
}
