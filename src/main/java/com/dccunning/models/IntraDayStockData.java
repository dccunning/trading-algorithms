package com.dccunning.models;

import java.time.LocalDateTime;

public class IntraDayStockData {
    private final String symbol;
    private final Double price;
    private final LocalDateTime timestamp;

    public IntraDayStockData(String symbol, Double price, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() { return symbol; }
    public Double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
