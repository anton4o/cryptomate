package integration;

import client.RestClient;
import junit.framework.*;
import model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JSONParser;
import util.CryptoMateUtils;

public class CryptoMateIntegrationTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(CryptoMateIntegrationTest.class);
    private RestClient client;
    private JSONParser parser;

    protected void setUp() {
        client = new RestClient("https://api.coinmarketcap.com/v1/ticker/");
        parser = new JSONParser();
    }

    public void testCryptoMate() {
        String ccyInput = CryptoMateUtils.getCurrencyName("zero");

        String response = client.get(ccyInput);
        Currency ccy = parser.parse(response);

        log.info("id: " + ccy.getId() +
                " ticker: " + ccy.getTicker() +
                " name: " + ccy.getName() +
                " price: " + ccy.getPrice());

        assertNotNull(ccy);
        assertNotNull(ccy.getId());
        assertNotNull(ccy.getTicker());
        assertNotNull(ccy.getName());
        assertNotNull(ccy.getPrice());
    }
}
