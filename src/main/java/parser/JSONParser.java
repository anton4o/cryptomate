package parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CurrencyPriceResponse;
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

    public CurrencyPriceResponse parse(String json) {

        CurrencyPriceResponse ccy = null;

        if (json != null) {

            try {
                TypeReference<List<CurrencyPriceResponse>> typeRef = new TypeReference<List<CurrencyPriceResponse>>() {
                };
                List<CurrencyPriceResponse> ccyList = mapper.readValue(json, typeRef);

                if (!ccyList.isEmpty()) {
                    ccy = ccyList.get(0);
                }
            } catch (IOException e) {
                log.error("Error while parsing JSON object: {}", e.getMessage());
            }

            if (ccy != null) {
                log.info("parsedCcy: {}, {}", ccy.getName(), ccy.getPrice());
            }
        }

        return ccy;
    }

    public List<CurrencyPriceResponse> parseAll(String json) {
        List<CurrencyPriceResponse> allCurrencies = new ArrayList<>();

        try {
            TypeReference<List<CurrencyPriceResponse>> typeRef = new TypeReference<List<CurrencyPriceResponse>>() { };
            List<CurrencyPriceResponse> ccyList = mapper.readValue(json, typeRef);

            if (!ccyList.isEmpty()) {
                allCurrencies = ccyList;
            }
        } catch (IOException e) {
            log.error("Error when parsing JSON object: {}", e.getMessage());
        }

        return allCurrencies;
    }
}
