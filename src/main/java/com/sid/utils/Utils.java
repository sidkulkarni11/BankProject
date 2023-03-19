package com.sid.utils;

public class Utils {

    public static String getTransactionType(TransactionType transactionType){
        if(transactionType.equals(TransactionType.DEPOSIT)){
            return "DEPOSIT";
        }
        else {
            return "WITHDRAW";
        }
    }
}
