package software.ulpgc.io.fixers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.io.ExchangeRateLoader;
import software.ulpgc.model.Currency;
import software.ulpgc.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class FixerExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            String json = loadJson();
            return parseExchangeRate(json, from, to);
        } catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
            return null;
        }
    }

    private ExchangeRate parseExchangeRate(String json, Currency from, Currency to) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

        JsonElement ratesElement = jsonObject.get("rates");
        if (ratesElement != null && ratesElement.isJsonObject()) {
            JsonObject rates = ratesElement.getAsJsonObject();

            if (rates.has(from.code()) && rates.has(to.code())) {
                double rateFrom = rates.get(from.code()).getAsDouble();
                double rateTo = rates.get(to.code()).getAsDouble();
                double exchangeRate = rateTo / rateFrom;
                return new ExchangeRate(from, to, LocalDate.now(), exchangeRate);
            }
        }
        System.out.println("Rates element does not contain more currencies.");
        return null;
    }

    private String loadJson() throws IOException {
        URL url = new URL("http://data.fixer.io/api/latest?access_key=" + FixerAPI.key);
        try (InputStream is = url.openStream()) {
            return  new String(is.readAllBytes());
        }
    }
}
