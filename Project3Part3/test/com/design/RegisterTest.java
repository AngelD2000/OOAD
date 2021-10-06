package com.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    @Test
    void incrementStoreTotal() {
        Register register = new Register();
        register.setStoreTotal(30);
        register.incrementStoreTotal(2500);
        Assertions.assertEquals(2530, register.getStoreTotal());

        register.setStoreTotal(250);
        register.incrementStoreTotal(-50);
        Assertions.assertEquals(200, register.getStoreTotal());
    }
    @Test
    /**
     * Check 1000 added to register when it is less than 100
     */
    void checkIfNeedFill() {
        int start = 99;
        Register register = new Register();
        register.setStoreTotal(start);
        register.checkIfNeedFill();
        Assertions.assertEquals(start+1000, register.getStoreTotal());
    }

}

