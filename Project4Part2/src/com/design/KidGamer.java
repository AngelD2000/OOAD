package com.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class KidGamer extends Customer {

    KidGamer(int num, String name) {
        super(num, name);
        setType(Util.customerTypes[1]);
        Util.setBonus(this);

    }
}
