package software.ulpgc.mocks;

import software.ulpgc.model.Currency;
import software.ulpgc.io.CurrencyLoader;

import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {
    @Override
    public List<Currency> load() {
        return List.of(
                new Currency("EUR", "Euro"),
                new Currency("USD", "Dolar"),
                new Currency("GBP", "Libra")
        );
    }
}
