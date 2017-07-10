package manual;


import client.RestClient;
import junit.framework.*;
import model.Currency;
import parser.JSONParser;

import java.util.List;
import java.util.stream.Collectors;

public class APITest extends TestCase {

    public void testGetAllCurrenciesFromAPI() {
        RestClient client = new RestClient("https://api.coinmarketcap.com/v1/ticker/");
        JSONParser parser = new JSONParser();

        String response = client.get("");
        List<Currency> allCurrencies = parser.parseAll(response);

        List<String> allNames = allCurrencies
                .stream()
                .map(ccy -> ccy.getName().toLowerCase())
                .collect(Collectors.toList());

        allNames.forEach(c -> System.out.println("- " + c));
    }

    public void testDiffs() {
        RestClient client = new RestClient("https://api.coinmarketcap.com/v1/ticker/");
        JSONParser parser = new JSONParser();

        String response = client.get("");
        List<Currency> allCurrencies = parser.parseAll(response);

        List<String> allDiffs = allCurrencies
                .stream()
                .filter(ccy -> !ccy.getName().toLowerCase().equals(ccy.getId().toLowerCase()))
                .map(ccy -> ccy.getName().toLowerCase() + " - " + ccy.getId())
                .collect(Collectors.toList());

        System.out.println("size: " + allDiffs.size());

        allDiffs.forEach(System.out::println);

    }
}
