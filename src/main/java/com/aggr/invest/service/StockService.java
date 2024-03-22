package com.aggr.invest.service;

import com.aggr.invest.controller.dto.CreateStockDto;
import com.aggr.invest.entity.Stock;
import com.aggr.invest.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;


    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);

    }
}
