import enums.StockType;
import enums.TradeType;
import lombok.Data;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Manage stock operations
 *
 * @author Manoj Kumar created on 17-02-2023
 */
@Data
public class Stock {
    private String symbol;
    private StockType type;
    private Double lastDividend;
    private Double fixedDividend;
    private Double parValue;
    private final TreeMap<Date, Trade> trades;

    public Stock(String symbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {
        this.setSymbol(symbol);
        this.setType(type);
        this.setLastDividend(lastDividend);
        this.setFixedDividend(fixedDividend);
        this.setParValue(parValue);
        this.trades = new TreeMap<>();
    }

    /**
     * Calculate the dividend based on the specified price
     *
     * @param price The price to use as a base to calculate the dividend
     * @return The dividend
     */
    public Double dividend(Double price) {
        switch(this.getType()) {
            case COMMON:
                return this.getLastDividend()/price;
            case PREFERRED:
                return this.getFixedDividend()*this.getParValue()/price;
            default:
                return 0.0;
        }
    }

    /**
     * Calculate P/E Ratio based on the specified price
     *
     * @param price The price to use as a base to calculate the P/E ratio
     * @return The P/E Ratio
     */
    public Double PERatio(Double price) {
        return price/this.getLastDividend();
    }

    /**
     * Buy stock, specifying quantity and price
     *
     * @param quantity The quantity of stock to BUY
     * @param price The price of the stock
     */
    public void buy(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.BUY, quantity, price);
        this.trades.put(new Date(), trade);
    }

    /**
     * Sell stock, specifying quantity and price
     *
     * @param quantity TYhe quantity of stock to SELL
     * @param price The price of the stock
     */
    public void sell(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.SELL, quantity, price);
        this.trades.put(new Date(), trade);
    }

    /**
     * Return the current price of the stock based on the last trade price
     *
     * @return The last trade price
     */
    public Double getPrice() {
        if (this.trades.size() > 0) {
            return this.trades.lastEntry().getValue().getPrice();
        } else {
            return 0.0;
        }
    }

    /**
     * Calculate the Volume Weighted Stock Price
     *
     * @return The Volume Weighted Stock Price
     */
    public Double calculateVolumeWeightedStockPrice() {
        Date now = new Date();
        Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
        SortedMap<Date, Trade> trades = this.trades.tailMap(startTime);
        double volumeWeigthedStockPrice = 0.0;
        Integer totalQuantity = 0;
        for (Trade trade: trades.values()) {
            totalQuantity += trade.getQuantity();
            volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
        }
        return volumeWeigthedStockPrice/totalQuantity;
    }
}
