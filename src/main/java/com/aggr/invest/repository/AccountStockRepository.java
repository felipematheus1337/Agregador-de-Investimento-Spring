package com.aggr.invest.repository;


import com.aggr.invest.entity.AccountStock;
import com.aggr.invest.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountStockRepository extends JpaRepository <AccountStock, AccountStockId> {
}
