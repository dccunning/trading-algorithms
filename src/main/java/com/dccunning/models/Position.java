package com.dccunning.models;

import java.time.LocalDate;

public class Position {
    private final String symbol;
    private final double entryPrice;
    private final double quantity;
    private final LocalDate entryDate;

    public Position(String symbol, double entryPrice, double quantity, LocalDate entryDate) {
        this.symbol = symbol;
        this.entryPrice = entryPrice;
        this.quantity = quantity;
        this.entryDate = entryDate;
    }

    public String getSymbol() {return symbol;}
    public double getEntryPrice() {return entryPrice;}
    public double getQuantity() {return quantity;}
    public LocalDate getEntryDate() {return entryDate;}
}
