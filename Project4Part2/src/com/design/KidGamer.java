package com.design;

public class KidGamer extends Customer {

    KidGamer(int num, String name) {
        super(num, name);
        setType(Util.customerTypes[1]);
    }
}
