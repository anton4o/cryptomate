import client.RestClient;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JSONParser;
import util.CryptoMateUtils;

public class CryptoMateSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(CryptoMateSpeechlet.class);

    private static final String SLOT_CURRENCY_NAME = "CurrencyName";

    private static final String REST_BASE_URL = "https://api.coinmarketcap.com/v1/ticker/";

    @Override
    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}",
                sessionStartedRequest.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}",
                launchRequest.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(),
                session.getSessionId());

        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("CryptoMatePriceIntent".equals(intentName)) {
            return getPriceResponse(intentRequest.getIntent());
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else if("AMAZON.StopIntent".equals(intentName) || "AMAZON.CancelIntent".equals(intentName)) {
                return getExitResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}",
                sessionEndedRequest.getRequestId(),
                session.getSessionId());
    }

    private SpeechletResponse getPriceResponse(final Intent intent) {
        String reply;
        String ccy = CryptoMateUtils.getCurrencyName(intent.getSlot(SLOT_CURRENCY_NAME).getValue());

        if (ccy == null) {
            reply = "Sorry, I did not understand the currency name";
        } else {
            RestClient client = new RestClient(REST_BASE_URL);
            JSONParser parser = new JSONParser();

            String response = client.get(ccy);
            Currency parsedCcy = parser.parse(response);

            reply = String.format("The current price of %s is %s dollars",
                    parsedCcy.getName(),
                    parsedCcy.getPrice());
        }


        SimpleCard card = new SimpleCard();
        card.setTitle("CryptoMate");
        card.setContent(reply);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(reply);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Welcome to Cryptomate, you can ask for the current price of any crypto currency."
                        + " For example, you can say bitcoin, ethereum, dash or ripple."
                        + " Which one would you like to know the current price of?";

        SimpleCard card = new SimpleCard();
        card.setTitle("CryptoMate");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        String repromptText = "Just say a crypto currency name and I will tell you the current price";

        PlainTextOutputSpeech speechReprompt = new PlainTextOutputSpeech();
        speech.setText(repromptText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speechReprompt);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "You can ask me about the price of any crypto currency."
                + " For example, you can say bitcoin, ethereum, dash or ripple";

        SimpleCard card = new SimpleCard();
        card.setTitle("CryptoMate");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        String repromptText = "Just say a crypto currency name and I will tell you the current price";

        PlainTextOutputSpeech speechReprompt = new PlainTextOutputSpeech();
        speech.setText(repromptText);

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speechReprompt);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getExitResponse() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("OK, bye");

        return SpeechletResponse.newTellResponse(outputSpeech);
    }
}