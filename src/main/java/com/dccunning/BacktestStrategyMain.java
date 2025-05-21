package com.dccunning;

import com.dccunning.clients.QuestradeClient;
import com.dccunning.models.IntraDayStockData;
import com.dccunning.strategies.BreakoutStrategy;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Run a Back test on a trading strategy.
 */
public class BacktestStrategyMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Start");

        QuestradeClient client = new QuestradeClient();
        IntraDayStockData data = client.getQuote(7960447);
        System.out.println(data.getPrice());

        List<HashMap<String, Object>> bmo = client.getSymbolsByPrefix("AC");
        System.out.println(bmo);

//        BreakoutStrategy breakout = new BreakoutStrategy(1000);
        // buy 2% of initial OR sell all
        // buy x, y, z
        // sell x, y; buy a, b, z
        //

        // 1. Define BreakoutStrategy() object
        // 2. Functions: buyToday(), sellToday(),

    }
}
