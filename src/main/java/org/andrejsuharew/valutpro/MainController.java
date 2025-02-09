package org.andrejsuharew.valutpro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.List;

public class MainController {

    @FXML
    private ComboBox<String> currencySelector;
    @FXML
    private Label rateLabel;
    @FXML
    private Button refreshButton;

    private final CurrencyService currencyService = new CurrencyService();

    @FXML
    public void initialize() {
        List<String> currencies = currencyService.getAvailableCurrencies();
        currencySelector.getItems().addAll(currencies);

        refreshButton.setOnAction(event -> {
            String selectedCurrency = currencySelector.getValue();
            if (selectedCurrency != null) {
                Currency currency = currencyService.getExchangeRate(selectedCurrency);
                rateLabel.setText(String.format("Курс к $: %.2f", currency.getRate()));
            }
        });
    }
}