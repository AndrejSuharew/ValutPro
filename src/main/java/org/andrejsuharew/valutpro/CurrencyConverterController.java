package org.andrejsuharew.valutpro;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CurrencyConverterController {
    @FXML
    private ComboBox<String> fromCurrencyBox;
    @FXML
    private ComboBox<String> toCurrencyBox;
    @FXML
    private TextField amountField;
    @FXML
    private Label resultLabel;
    @FXML
    private Button convertButton;

    private final CurrencyConverter currencyConverter = new CurrencyConverter();

    @FXML
    public void initialize() {
        try {
            currencyConverter.loadExchangeRates("USD"); // Загружаем курсы с базовой валютой USD
            fromCurrencyBox.getItems().addAll(currencyConverter.getSortedCurrencies());
            toCurrencyBox.getItems().addAll(currencyConverter.getSortedCurrencies());
            fromCurrencyBox.setValue("USD");
            toCurrencyBox.setValue("EUR");
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Ошибка загрузки курсов валют. Проверьте интернет-соединение.");
            convertButton.setOnAction(event -> convertCurrency());
        }
    }

    @FXML
    public void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = fromCurrencyBox.getValue();
            String toCurrency = toCurrencyBox.getValue();
            double result = currencyConverter.convert(amount, fromCurrency, toCurrency);
            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency));
        } catch (NumberFormatException e) {
            resultLabel.setText("Введите корректное число.");
        } catch (Exception e) {
            resultLabel.setText("Ошибка конвертации.");
        }
    }
}