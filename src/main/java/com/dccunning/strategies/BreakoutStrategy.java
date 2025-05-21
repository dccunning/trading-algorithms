package com.dccunning.strategies;

import com.dccunning.models.DailyMarketSnapshot;
import com.dccunning.models.IntraDayStockData;
import com.dccunning.models.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BreakoutStrategy {
    // Enter trades when price breaks out of recent resistance levels with strong volume
    // Buy $AMOUNT of stock X if: (ALL must be true)
    // 1. Last closing price > 20-day high
    // 2. Last day’s volume > 1.5 × 20-day average volume
    // 3. 20-day MA > 50-day MA (optional)

    // Sell 100% of stock X if: (ANY of these is true)
    // 1. Price reaches 10% profit from entry
    // 2. Price falls 5% below entry
    // 3. Price closes below entry price for 2 consecutive days
    // 4. Holding period exceeds 10 market days

    private static final double PERCENT_OF_PORTFOLIO = 0.05;
    private final double initialPortfolioAmount;
    private double cashAvailable;
    private List<Position> holdings;


    public BreakoutStrategy(double initialPortfolioAmount) {
        this.initialPortfolioAmount = initialPortfolioAmount;
        this.holdings = new ArrayList<>();
    }

    // Called once per day, e.g. 15 min after market opens
    public void buyToday(DailyMarketSnapshot marketSnapshot) {
        // stock, last_close_price, last_close_volume, 20_day_high, 20_day_avg_volume, 20_day_ma, 50_day_ma
        // Use today's market snapshot data to decide which stocks to buy, and buy them
        Position position = new Position("AAPL", 100.0, 12.12, LocalDate.now());
        holdings.add(position);
        System.out.println(holdings);
    }

    // Called every 5 min during market hours
    public void sell(List<IntraDayStockData> currentPortfolioPrices) {
        // Use the current holdings info and current prices to decide all sell orders, and sell them
        Position position = new Position("AAPL", 100.0, 12.12, LocalDate.now());
        holdings.add(position);
        System.out.println(holdings);
    }
}
