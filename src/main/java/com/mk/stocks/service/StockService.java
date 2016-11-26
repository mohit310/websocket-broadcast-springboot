package com.mk.stocks.service;


import com.mk.stocks.dao.StockDAO;
import com.mk.stocks.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by mk on 11/20/16.
 */
@Service
public class StockService {

    private StockDAO stockDAO;

    @Autowired
    public StockService(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public Stock getPrice(String ticker) {
        return stockDAO.getPrice(ticker);
    }

    public List<Stock> getPrices(List<String> tickers) {
        return tickers.parallelStream().map(ticker -> stockDAO.getPrice(ticker)).collect(toList());
    }
}
