package model;


import java.math.BigDecimal;

public class CurrencyPriceModel {

    private String id;
    private String ticker;
    private String name;
    private BigDecimal price;
    private Double marketCap;
    private Double pctChange24h;

    public CurrencyPriceModel(CurrencyPriceResponse rawResponse) {

        this.id = rawResponse.getId();
        this.ticker = rawResponse.getTicker();
        this.name = rawResponse.getName();
        this.price = formatPrice(rawResponse.getPrice());
        this.marketCap = rawResponse.getMarketCap();
        this.pctChange24h = rawResponse.getPctChange24h();
    }

    public String getId() {
        return this.id;
    }

    public String getTicker() {
        return this.ticker;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() { return this.price; }

    public Double getMarketCap() { return this.marketCap; }

    public Double getPctChange24h() { return this.pctChange24h; }

    private static BigDecimal formatPrice(Double price) {
        return BigDecimal.valueOf(price).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}

