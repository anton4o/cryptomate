package parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private static final Logger log = LoggerFactory.getLogger(JSONParser.class);

    private ObjectMapper mapper;

    public JSONParser() {
        this.mapper = new ObjectMapper();
    }

    public Currency parse(String json) {

        Currency ccy = null;

        try {
            TypeReference<List<Currency>> typeRef = new TypeReference<List<Currency>>() { };
            List<Currency> ccyList = mapper.readValue(json, typeRef);

            if (!ccyList.isEmpty()) {
                ccy = ccyList.get(0);
            }
        } catch (IOException e) {
            log.error("Error when parsing JSON object: {}", e.getMessage());
        }

        return ccy;
    }

    public List<Currency> parseAll(String json) {
        List<Currency> allCurrencies = new ArrayList<>();

        try {
            TypeReference<List<Currency>> typeRef = new TypeReference<List<Currency>>() { };
            List<Currency> ccyList = mapper.readValue(json, typeRef);

            if (!ccyList.isEmpty()) {
                allCurrencies = ccyList;
            }
        } catch (IOException e) {
            log.error("Error when parsing JSON object: {}", e.getMessage());
        }

        return allCurrencies;
    }
}
