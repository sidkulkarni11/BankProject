package com.sid.test;

import com.sid.model.BankTransacation;
import com.sid.service.BankService;

import java.util.List;

public class BankTestClass {
    public static void main(String[] args) {
        BankService bankService = new BankService();

        List<BankTransacation> bankTransacationList = bankService.getAllBankTransactions();

        for (BankTransacation bankTranaction:
                bankTransacationList ) {

            System.out.println(bankTranaction.getName());
        }
    }
}
