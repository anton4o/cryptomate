package util;

public class CryptoMateUtils {

    public static String getCurrencyName(String ccyInput) {
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
