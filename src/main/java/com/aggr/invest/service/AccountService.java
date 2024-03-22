package com.aggr.invest.service;


import com.aggr.invest.controller.dto.AccountStockResponseDto;
import com.aggr.invest.controller.dto.AssociateAccountStockDto;
import com.aggr.invest.entity.AccountStock;
import com.aggr.invest.entity.AccountStockId;
import com.aggr.invest.repository.AccountRepository;
import com.aggr.invest.repository.AccountStockRepository;
import com.aggr.invest.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {


    private AccountRepository accountRepository;

    private StockRepository stockRepository;

    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
    }

    public void asssociateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(ac -> new AccountStockResponseDto(ac.getStock().getStockId(), ac.getQuantity(),0.0))
                .toList();
    }
}
