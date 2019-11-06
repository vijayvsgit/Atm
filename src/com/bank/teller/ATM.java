package com.bank.teller;

import java.util.*;

public class ATM implements IATM{

    int balance = 0;
    Map<Integer, Integer> currencyMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());;

    ATM(){
        for(Integer denomination : com.bank.teller.Currency.getAvailableDenominationsList()){
            currencyMap.put(denomination,0);
        }
    }

    public Map<Integer, Integer> getCurrencyMap() {
        return currencyMap;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public Map<Integer,Integer> deposit(List<TransactionDetails> transactionDetailsList) {
        for(TransactionDetails transactionDetails : transactionDetailsList){
            int existingCount =0,currentCount=0;

            int denomination = transactionDetails.getDenomination();
            if(currencyMap.get(denomination) != null)
                existingCount = currencyMap.get(denomination);

            currentCount = transactionDetails.getCount();

            int totalCount = existingCount + currentCount;
            currencyMap.put(denomination,totalCount);

            setBalance(getBalance()+(denomination * currentCount));
        }
        return currencyMap;
    }

    @Override
    public List<TransactionDetails> dispense(int amount) {
        HashMap<Integer, Integer> dispenseMap = new HashMap<>();
        Iterator itr = currencyMap.keySet().iterator();
        while(itr.hasNext() && amount!=0){
            int key = (Integer)itr.next();
            int count = currencyMap.get(key);
            if(count!=0)
            {
                int quotient = amount/key;
                int dispensibleCount=0;

                if(quotient >=1 && count > 0){
                    dispensibleCount = quotient>count?count:quotient;
                    dispenseMap.put(key,dispensibleCount);
                }
                amount = amount - (key * dispensibleCount);
            }
        }
        if(amount==0){
            dispenseStr(dispenseMap);
        }else{
            System.out.println("Denominations not available to dispense the requested amount");
            System.out.println("Available Denominations: " + Currency.getAvailableDenominationsList() + "\n" + display());
        }

        return null;
    }

    private void dispenseStr(HashMap<Integer, Integer>  dispenseMap){
        Iterator itr = dispenseMap.keySet().iterator();
        StringBuffer dispenseStr = new StringBuffer();
        dispenseStr.append("Dispensed : ");
        while(itr.hasNext()){
            int key = (Integer)itr.next();
            int count = dispenseMap.get(key);
            dispenseStr.append(key + "s=" + count + ",");
            currencyMap.put(key,currencyMap.get(key)-count);
            setBalance(getBalance()-(key * count));
        }
        System.out.println(dispenseStr);
        System.out.println(display());
    }

    private StringBuffer display(){
        Iterator itr = currencyMap.keySet().iterator();
        StringBuffer displayStr = new StringBuffer ("Balance : ");
        while(itr.hasNext()){
            int key = (Integer)itr.next();
            int count = currencyMap.get(key);
            displayStr.append(key + "s=" + count + ",");
        }
        displayStr.append("Total="+getBalance()+"\n");
        return displayStr;
    }

}
