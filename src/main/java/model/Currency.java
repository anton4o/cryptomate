package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    private String id;
    private String ticker;
    private String name;
    private Double price;

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    @JsonProperty("symbol")
    public String getTicker() {
        return this.ticker;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("price_usd")
    public Double getPrice() { return this.price; }
}
