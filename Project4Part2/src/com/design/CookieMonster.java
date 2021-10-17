package com.design;

public class CookieMonster extends Customer {

    CookieMonster(int num, String name) {
        super(num, name);
        setCookieMonster(true);
        setType(Util.customerTypes[4]);
    }
}
