package com.mk.stocks.dao;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.stocks.entity.Stock;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created by mk on 11/24/16.
 */
@Repository
public class StockDAO {

    private static final String STOCK_URL = "https://www.google.com/finance/info?q=";

    public Stock getPrice(String ticker) throws RuntimeException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        Stock stock = null;
        try {
            URL url = new URL(STOCK_URL + ticker);
            stock = mapper.readValue(url, Stock.class);
            //stock.setPrice(new BigDecimal(Math.random()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }
}
