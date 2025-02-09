package org.andrejsuharew.valutpro;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public Currency getExchangeRate(String currencyCode) {
        String response = HttpClientUtil.get(API_URL);
        JSONObject jsonObject = new JSONObject(response);
        double rate = jsonObject.getJSONObject("rates").getDouble(currencyCode);
        return new Currency(currencyCode, rate);
    }

    public List<String> getAvailableCurrencies() {
        String response = HttpClientUtil.get(API_URL);
        JSONObject jsonObject = new JSONObject(response);
        return new ArrayList<>(jsonObject.getJSONObject("rates").keySet());
    }
}