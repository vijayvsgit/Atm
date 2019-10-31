package com.bank.teller;

import java.util.List;

public class TransactionValidator {
    public boolean validateZeroDeposit(List<TransactionDetails> transactionDetailsList){
        boolean zeroDeposit = true;
        for(TransactionDetails transactionDetails : transactionDetailsList){
            if((transactionDetails.getDenomination() * transactionDetails.getCount()) !=0 )
                zeroDeposit = false;
        }
        return zeroDeposit;
    }
}
