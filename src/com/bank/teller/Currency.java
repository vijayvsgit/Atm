package com.bank.teller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Currency {

    static Integer[] availableDenominations = {20,10,5,1};
    static List<Integer> availableDenominationsList = new ArrayList<>();

    static {
        availableDenominationsList.addAll(Arrays.asList(availableDenominations));
    }

    public static List<Integer> getAvailableDenominationsList() {
        return availableDenominationsList;
    }

    public void setAvailableDenominationsList(List<Integer> availableDenominationsList) {
        this.availableDenominationsList = availableDenominationsList;
    }


}
