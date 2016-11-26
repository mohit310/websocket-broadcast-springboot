package com.mk.stocks.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by mk on 11/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    @JsonProperty
    private long id;
    @JsonProperty("t")
    private String ticker;
    @JsonProperty("e")
    private String exchange;
    @JsonProperty("l")
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", exchange='" + exchange + '\'' +
                ", price=" + price.toPlainString() +
                '}';
    }
}
