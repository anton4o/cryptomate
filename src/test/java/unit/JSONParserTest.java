package unit;


import junit.framework.TestCase;
import model.Currency;
import parser.JSONParser;

public class JSONParserTest extends TestCase {

    private JSONParser parser;

    private final String validJson = "[\n" +
            "    {\n" +
            "        \"id\": \"bitcoin\", \n" +
            "        \"name\": \"Bitcoin\", \n" +
            "        \"symbol\": \"BTC\", \n" +
            "        \"rank\": \"1\", \n" +
            "        \"price_usd\": \"2598.25\", \n" +
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

    public void testParseSuccessful() {
        Currency parsed = parser.parse(validJson);

        assertNotNull(parsed);
        assertEquals("Bitcoin", parsed.getName());
        assertEquals(2598.25, parsed.getPrice());
    }

    public void testParseUnsuccessful() {
        assertNull(parser.parse(""));
    }

    public void testParseNull() {
        assertNull(parser.parse(null));
    }
}
