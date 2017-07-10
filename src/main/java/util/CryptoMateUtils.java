package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoMateUtils {

    private static final Logger log = LoggerFactory.getLogger(CryptoMateUtils.class);

    public static String getCurrencyName(String ccyInput) {
        log.info("ccyInput: {}", ccyInput);

        if (ccyInput == null || ccyInput.isEmpty()) {
            return null;
        }

        String validCcy;

        if (ccyInput.contains(" ")) {
            validCcy = ccyInput.replaceAll("\\s", "-");
        } else {
            validCcy = ccyInput;
        }

        return validCcy.toLowerCase();
    }
}
