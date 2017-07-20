package unit;

import junit.framework.*;
import util.CryptoMateUtils;

public class CryptoMateUtilsTest extends TestCase {

    public void testGetCurrencyName() {
        assertEquals("bitcoin", CryptoMateUtils.getCurrencyName("bitcoin"));

        assertEquals("bitcoin", CryptoMateUtils.getCurrencyName("Bitcoin"));

        assertEquals("ethereum-classic", CryptoMateUtils.getCurrencyName("ethereum classic"));

        assertEquals(null, CryptoMateUtils.getCurrencyName(""));
    }

    public void testSupportedCcys() {
        CryptoMateUtils utils = new CryptoMateUtils();

        assertEquals(435, utils.getSupportedCcys().size());
        assertTrue(utils.isSupported("bitcoin"));
        assertFalse(utils.isSupported("lev"));
        assertTrue(utils.isSupported("ethereum-classic"));
        assertFalse(utils.isSupported("ethereum classic"));
    }
}
