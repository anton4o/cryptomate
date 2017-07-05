import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class CryptoMateSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        supportedApplicationIds.add(System.getenv("cryptoMateAppId"));
    }

    public CryptoMateSpeechletRequestStreamHandler() {
        super(new CryptoMateSpeechlet(), supportedApplicationIds);
    }
}