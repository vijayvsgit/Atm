package com.bank.teller;

public class TransactionDetails {

    TransactionDetails(int denomination, int count){
        this.denomination = denomination;
        this.count = count;
    }
    public int getDenomination() {
        return denomination;
    }

    public int getCount() {
        return count;
    }

    private int denomination;
    private int count;


}
