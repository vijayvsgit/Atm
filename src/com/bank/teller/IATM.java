package com.bank.teller;

import java.util.List;
import java.util.Map;

public interface IATM {
    public Map<Integer,Integer> deposit(List<TransactionDetails> transactionDetailsList);
    public List<TransactionDetails> dispense(int amount);
    public Map<Integer, Integer> getCurrencyMap();
    public int getBalance();
}
