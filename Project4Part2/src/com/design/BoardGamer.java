package com.design;

public class BoardGamer extends Customer {

    BoardGamer(int num, String name) {
        super(num, name);
        setType(Util.customerTypes[3]);
        Util.setBonus(this);
    }
}
