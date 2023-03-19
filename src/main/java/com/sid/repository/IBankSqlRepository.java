package com.sid.repository;

import com.sid.model.BankMaster;
import com.sid.model.BankTransacation;

import java.util.List;

public interface IBankSqlRepository {
    List<BankTransacation> getAllBankTransactions();

    void register(BankMaster bankMaster ,BankTransacation bankTransacation);
}
