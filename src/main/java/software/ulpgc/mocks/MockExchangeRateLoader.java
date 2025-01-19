package software.ulpgc.mocks;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;
import software.ulpgc.model.ExchangeRateLoader;

import java.time.LocalDate;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return new ExchangeRate(from, to, LocalDate.now(), 1.218);
    }
}
