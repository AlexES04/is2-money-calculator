package mocks;

import control.Command;
import control.ExchangeMoneyCommand;
import model.Currency;
import model.ExchangeRateLoader;
import view.CurrencyDialog;
import view.MoneyDialog;
import view.MoneyDisplay;

import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        List<Currency> currencies = new MockCurrencyLoader().load();
        MoneyDialog moneyDialog = new MockMoneyDialog().define(currencies);
        CurrencyDialog currencyDialog = new MockCurrencyDialog().define(currencies);
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();
        ExchangeRateLoader exchangeRateLoader = new MockExchangeRateLoader();
        Command command = new ExchangeMoneyCommand(moneyDialog, currencyDialog, exchangeRateLoader, moneyDisplay);
        command.execute();
    }
}
