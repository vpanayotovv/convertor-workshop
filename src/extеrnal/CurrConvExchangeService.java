package extеrnal;

import domain.entity.Money;
import domain.external.ExchangeService;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrConvExchangeService implements ExchangeService {

    private final String API_KEY = System.getenv("API_KEY");

    @Override
    public Money exchange(Money convertFrom, String toCurrency) {

        BigDecimal rate = makeRequest(convertFrom.getCurrency(), toCurrency);
        BigDecimal exchangedValue = convertFrom.getValue().multiply(rate);

        return new Money(exchangedValue, toCurrency);
    }

    private BigDecimal makeRequest(String currencyFrom, String currencyTo) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(buildRequestUrl(currencyFrom, currencyTo))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return extractExchangeRate(response.body());
        } catch (Exception ex) {
            throw new RuntimeException("Http request to external API failed");
        }
    }


    private BigDecimal extractExchangeRate(String response) {
        int columnIndex = response.lastIndexOf(":");
        int endBracketIndex = response.lastIndexOf("}");
        return new BigDecimal(response.substring(columnIndex + 1, endBracketIndex));
    }

    private URI buildRequestUrl(String from, String to) {

        String url =
                String.format(
                        "https://free.currconv.com/api/v7/convert?apiKey=%s&q=%s_%s&compact=ultra", API_KEY, from, to);

        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("URI failed to build");
        }
    }
}
