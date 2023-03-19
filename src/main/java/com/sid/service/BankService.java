package com.sid.service;

import com.sid.model.BankTransacation;
import com.sid.repository.BankSqlRepository;
import com.sid.repository.IBankSqlRepository;

import java.util.List;

public class BankService {
   private IBankSqlRepository bankSqlRepository;

    public BankService() {
        bankSqlRepository = new BankSqlRepository();
    }

    public List<BankTransacation> getAllBankTransactions(){
        List<BankTransacation> bankTransacationList = bankSqlRepository.getAllBankTransactions();


        return  bankTransacationList;
    }

}
