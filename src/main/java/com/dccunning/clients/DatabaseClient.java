package com.dccunning.clients;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.List;

/**
 * Query Postgres database for trading data.
 */
@Component
public class DatabaseClient {
    private final JdbcTemplate jdbc;

    public DatabaseClient(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> runQuery(String sql) {
        return jdbc.queryForList(sql);
    }

}