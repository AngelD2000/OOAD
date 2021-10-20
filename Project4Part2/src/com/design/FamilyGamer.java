package com.design;

public class FamilyGamer extends Customer {

    FamilyGamer(int num, String name) {
        super(num, name);
        setType(Util.customerTypes[0]);
        Util.setBonus(this);
    }
}
