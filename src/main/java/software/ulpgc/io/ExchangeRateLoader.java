package software.ulpgc.io;

import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
