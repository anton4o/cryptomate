package unit;

import junit.framework.TestCase;
import model.CurrencyPriceModel;
import model.CurrencyPriceResponse;
import parser.JSONParser;

import java.math.BigDecimal;

public class CurrencyPriceModelTest extends TestCase  {

    private JSONParser parser;

    private final String validJson = "[\n" +
            "    {\n" +
            "        \"id\": \"bitcoin\", \n" +
            "        \"name\": \"Bitcoin\", \n" +
            "        \"symbol\": \"BTC\", \n" +
            "        \"rank\": \"1\", \n" +
            "        \"price_usd\": \"2598.212345235\", \n" +
            "        \"price_btc\": \"1.0\", \n" +
            "        \"24h_volume_usd\": \"951519000.0\", \n" +
            "        \"market_cap_usd\": \"42689083810.0\", \n" +
            "        \"available_supply\": \"16429937.0\", \n" +
            "        \"total_supply\": \"16429937.0\", \n" +
            "        \"percent_change_1h\": \"-0.52\", \n" +
            "        \"percent_change_24h\": \"0.6\", \n" +
            "        \"percent_change_7d\": \"0.92\", \n" +
            "        \"last_updated\": \"1499290756\"\n" +
            "    }\n" +
            "]";

    public void setUp() {
        parser = new JSONParser();
    }

    public void testCorrectInstantiation() {
        CurrencyPriceResponse parsed = parser.parse(validJson);
        CurrencyPriceModel model = new CurrencyPriceModel(parsed);

        BigDecimal expectedPrice = new BigDecimal(2598.212).setScale(3, BigDecimal.ROUND_HALF_UP);

        assertNotNull(model);
        assertEquals("Bitcoin", model.getName());
        assertEquals(expectedPrice, model.getPrice());
        assertEquals(42689083810.0, parsed.getMarketCap());
        assertEquals(0.6, parsed.getPctChange24h());
    }
}
