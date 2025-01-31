package software.ulpgc;

import software.ulpgc.io.fixers.FixerCurrencyLoader;
import software.ulpgc.model.Currency;
import software.ulpgc.io.CurrencyLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new FixerCurrencyLoader();
        List<Currency> currencies = currencyLoader.load();
        for (Currency currency : currencies) {
            System.out.println(currency);
        }
    }
}