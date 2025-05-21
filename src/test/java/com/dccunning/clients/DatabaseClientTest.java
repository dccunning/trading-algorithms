package com.dccunning.clients;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseClientTest {
    @Test
    void selectAllStocks() {
        Dotenv dotenv = Dotenv.load();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dotenv.get("DB_URL"));
        dataSource.setUsername(dotenv.get("DB_USER"));
        dataSource.setPassword(dotenv.get("DB_PASS"));

        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        DatabaseClient db = new DatabaseClient(jdbc);

        String sql = "select symbol, time from crypto.binance_api_price where time = 1744460100383 and symbol = 'GMXUSDT';";
        List<Map<String, Object>> actualResult = db.runQuery(sql);

        List<Map<String, Object>> expectedResult = List.of(
                Map.of("symbol", "GMXUSDT", "time",1744460100383L)
        );
        assertEquals(expectedResult, actualResult);
    }
}
