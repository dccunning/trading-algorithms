package com.dccunning.clients;

import com.dccunning.models.IntraDayStockData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Make trades and get portfolio data from Quest Trade
 */
public class QuestradeClient {

    private static final Path TOKEN_FILE = Path.of("refresh_token.txt");

    private String refreshToken;
    private String accessToken;
    private String apiServer;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public QuestradeClient() throws IOException, InterruptedException {
        this.refreshToken = Files.readString(TOKEN_FILE).trim();
        authenticate();                // sets accessToken, apiServer, refreshToken
    }

    private void authenticate() throws IOException, InterruptedException {
        String form = "grant_type=refresh_token&refresh_token="
                + URLEncoder.encode(refreshToken.trim(), StandardCharsets.UTF_8);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("https://login.questrade.com/oauth2/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() != 200)
            throw new IOException("Auth failed: " + resp.body());

        JSONObject json  = new JSONObject(resp.body());
        accessToken      = json.getString("access_token");
        apiServer        = json.getString("api_server");
        refreshToken     = json.getString("refresh_token");
        Files.writeString(TOKEN_FILE, refreshToken);
    }

    public String getAccounts() throws IOException, InterruptedException {
        String url = apiServer + "v1/accounts";
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();
        return httpClient.send(req, HttpResponse.BodyHandlers.ofString()).body();
    }

    public IntraDayStockData getQuote(int symbolId) throws IOException, InterruptedException {
        String url = apiServer + "v1/markets/quotes/" + symbolId;
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        JSONObject quote = new JSONObject(
                httpClient.send(req, HttpResponse.BodyHandlers.ofString()).body()
        ).getJSONArray("quotes").getJSONObject(0);

        String symbol     = quote.getString("symbol");

        double price      = quote.optDouble("lastTradePrice", quote.optDouble("bidPrice",  Double.NaN));

        String tsRaw  = quote.optString("lastTradeTime", null);     // null-safe
        LocalDateTime ts = tsRaw == null
                ? null
                : LocalDateTime.parse(tsRaw, DateTimeFormatter.ISO_DATE_TIME);

        if (Double.isNaN(price) || ts == null) {
            System.out.println("No quote data for " + symbol);
            return new IntraDayStockData(symbol, null, ts);
        }

        return new IntraDayStockData(symbol, price, ts);
    }

    public List<HashMap<String, Object>> getSymbolsByPrefix(String prefix)
            throws IOException, InterruptedException {

        String base = apiServer.endsWith("/v1/") ? apiServer : apiServer + "v1/";
        String url  = base + "symbols/search?prefix="
                + URLEncoder.encode(prefix, StandardCharsets.UTF_8);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> resp =
                httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200)
            throw new IOException("Symbol search failed: " + resp.body());

        JSONArray arr = new JSONObject(resp.body()).getJSONArray("symbols");

        List<HashMap<String, Object>> symbols = new ArrayList<>(arr.length());
        for (int i = 0; i < arr.length(); i++)
            symbols.add(new HashMap<>(arr.getJSONObject(i).toMap()));

        return symbols;
    }
}