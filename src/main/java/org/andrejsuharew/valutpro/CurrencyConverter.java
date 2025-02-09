package org.andrejsuharew.valutpro;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class CurrencyConverter {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private Map<String, Double> exchangeRates = new HashMap<>();

    public void loadExchangeRates(String baseCurrency) throws IOException {
        URL url = new URL(API_URL + baseCurrency);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonObject rates = json.getAsJsonObject("rates");

        exchangeRates.clear();
        for (String key : rates.keySet()) {
            exchangeRates.put(key, rates.get(key).getAsDouble());
        }
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Некорректный код валюты");
        }
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        return amount * (toRate / fromRate);
    }

    public List<String> getSortedCurrencies() {
        List<String> currencies = new ArrayList<>(exchangeRates.keySet());
        Collections.sort(currencies);
        return currencies;
    }
}
