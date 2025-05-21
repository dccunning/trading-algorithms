package com.dccunning.models;

public class DailyStockData {
    private final Double lastClosePrice;
    private final Double lastCloseVolume;
    private final Double highestHigh20Day;
    private final Double averageVolume20Day;
    private final Double sma20Day;
    private final Double sma50Day;

    public DailyStockData(
            Double lastClosePrice,
            Double lastCloseVolume,
            Double highestHigh20Day,
            Double averageVolume20Day,
            Double sma20Day,
            Double sma50Day
    ) {
        this.lastClosePrice = lastClosePrice;
        this.lastCloseVolume = lastCloseVolume;
        this.highestHigh20Day = highestHigh20Day;
        this.averageVolume20Day = averageVolume20Day;
        this.sma20Day = sma20Day;
        this.sma50Day = sma50Day;
    }

    public Double getLastClosePrice() {
        return lastClosePrice;
    }

    public Double getLastCloseVolume() {
        return lastCloseVolume;
    }

    public Double getHighestHigh20Day() {
        return highestHigh20Day;
    }

    public Double getAverageVolume20Day() {
        return averageVolume20Day;
    }

    public Double getSma20Day() {
        return sma20Day;
    }

    public Double getSma50Day() {
        return sma50Day;
    }
}
