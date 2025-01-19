package software.ulpgc.fixers;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;
import software.ulpgc.model.ExchangeRateLoader;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        return null;
    }
}
