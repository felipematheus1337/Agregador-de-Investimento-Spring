package com.aggr.invest.repository;

import com.aggr.invest.entity.Account;
import com.aggr.invest.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface BillingAddressRepository extends JpaRepository <BillingAddress, UUID> {
}
