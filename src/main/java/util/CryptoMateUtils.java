package util;

public class CryptoMateUtils {

    public static String getCurrencyName(String ccyInput) {
        if (ccyInput == null || ccyInput.isEmpty()) {
            return null;
        }

        return ccyInput.toLowerCase();
    }
}
