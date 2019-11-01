package com.bank.teller;

import java.util.*;

public class Currency {

    static Integer[] availableDenominations = {20,10,5,1};
    static Set<Integer> availableDenominationsList = new TreeSet<Integer>(Collections.reverseOrder());

    static {
        availableDenominationsList.addAll(Arrays.asList(availableDenominations));
    }

    public static Set<Integer> getAvailableDenominationsList() {
        return availableDenominationsList;
    }

}
