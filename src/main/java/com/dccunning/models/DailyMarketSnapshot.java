package com.dccunning.models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public class DailyMarketSnapshot {
    private final HashMap<String, DailyStockData> data; // symbol: stockData
    private final LocalDate date;

    /**
     * Market data points calculated for some stocks at a point in time.
     * @param data Stock symbol and row of stock data
     * @param date of the stock data snapshot
     */
    public DailyMarketSnapshot(
            HashMap<String, DailyStockData> data,
            LocalDate date
    ) {
        this.data = data;
        this.date = date;
    }

    public DailyStockData get(String symbol) {
        return data.get(symbol);
    }

    public Set<String> getSymbols() {
        return data.keySet();
    }
}
