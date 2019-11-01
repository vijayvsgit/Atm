package com.bank.teller;

import java.util.*;

public class MainInterface {
    static ATM atm = new ATM();
    public static void main(String[] args) {
        Map<Integer,Integer> currencyMap = null;
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            System.out.println("Enter 1 to Deposit, 2 to Withdraw, 3 to add Denominations, 4 to Exit : ");
            choice = input.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Input is not in the right format");
            return;
        }


        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        TransactionDetails transactionDetails = null;
        String token = null;


        while (choice !=4){
            switch (choice){
                case 1:
                    System.out.println("Available Denominations: " + com.bank.teller.Currency.getAvailableDenominationsList());
                    System.out.println("Enter the details to Deposit in the format of Denomiation:count, Denomination:count, ...");
                    String depositDetails = input.next();
                    StringTokenizer stringTokenizer = new StringTokenizer(depositDetails,",");
                    while(stringTokenizer.hasMoreTokens()){
                        token = stringTokenizer.nextToken();
                        String currencyDetails[] = token.split(":");
                        if(currencyDetails!=null & currencyDetails.length >1){
                            try{
                                int denomination = Integer.valueOf(currencyDetails[0]);
                                int count = Integer.valueOf(currencyDetails[1]);
                                if(denomination <0 || count<0){
                                    System.out.println("Incorrect deposit amount");
                                    break;
                                }
                                if(!atm.getCurrencyMap().containsKey(denomination)){
                                    System.out.println("Invalid denomination");
                                    break;
                                }
                                transactionDetails = new TransactionDetails(denomination,count);
                                transactionDetailsList.add(transactionDetails);

                            }catch (NumberFormatException e){
                                System.out.println("Invalid input ");
                                return;
                            }
                        }else{
                            System.out.println("Input is not in the right format");
                            break;
                        }
                    }
                    TransactionValidator transactionValidator = new TransactionValidator();
                    if(transactionValidator.validateZeroDeposit(transactionDetailsList)){
                        System.out.println("Deposit amount cannot be zero");
                        break;
                    }
                    currencyMap = atm.deposit(transactionDetailsList);
                    Iterator itr = currencyMap.keySet().iterator();
                    System.out.print("Balance : ");
                    int total = 0;
                    while(itr.hasNext()){
                        int key = (Integer)itr.next();
                        int count = currencyMap.get(key);
                        System.out.print(key + "s=" + count + ",");
                        //total = total + (key * count);
                    }
                    System.out.print("Total="+atm.getBalance());
                    break;

                case 2:
                    int amount = 0;
                    try{
                        System.out.println("Enter the amount to withdraw : ");
                        amount = input.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Input is not in the right format");
                        return;
                    }
                    if(amount<=0 || amount>atm.getBalance()){
                        System.out.println("Incorrect or insufficient funds");
                        break;
                    }
                    atm.dispense(amount);
                    break;

                case 3:
                    int denomination=0;
                    try{
                        System.out.println("Enter the Denomination to add");
                        denomination = input.nextInt();
                        if(denomination >0){
                            Currency.getAvailableDenominationsList().add(denomination);
                            if(atm.getCurrencyMap().get(denomination)==null)
                                atm.getCurrencyMap().put(denomination,0);
                        }

                        System.out.println("Available Denominations: " + com.bank.teller.Currency.getAvailableDenominationsList());
                    }catch(InputMismatchException e){
                        System.out.println("Input is not in the right format");
                        return;
                    }
                default:
                    break;
            }
            transactionDetailsList.clear();
            try{
                System.out.println("\nEnter 1 to Deposit, 2 to Withdraw, 3 to add Denominations, 4 to Exit : ");
                choice = input.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Input is not in the right format");
                return;
            }
        }
        input.close();
    }
}
