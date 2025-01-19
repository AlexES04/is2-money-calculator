package software.ulpgc.model;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to);
}
