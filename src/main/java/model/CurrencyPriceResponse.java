package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyPriceResponse {

    private String id;
    private String ticker;
    private String name;
    private Double price;
    private Double marketCap;
    private Double pctChange24h;

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

    @JsonProperty("market_cap_usd")
    public Double getMarketCap() { return this.marketCap; }

    @JsonProperty("percent_change_24h")
    public Double getPctChange24h() { return this.pctChange24h; }
}
