package com.design;

public class CardGamer extends Customer {

    CardGamer(int num, String name) {
        super(num, name);
        setType(Util.customerTypes[2]);
        Util.setBonus(this);
    }
}
