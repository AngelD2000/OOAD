package com.design;

import java.util.Random;

public class Customer {
    int cookiesConsumed = Util.noneConsumed;
    Boolean cookieMonster = false;
    int customerNum;
    int additional_odds = 0;
    Customer(int num){
        cookieMonster = Util.testOdds(Util.monsterChance);
        customerNum = num;
    }
    /**
     * Customer decides how many cookies they want
     * Sets the cookiesConsumed if the customer ate, didn't eat, or wanted more cookies
     */
    String considerCookies(Store store){
        //Check if customer wants any cookies
        String choice = "didn't want any cookies";
        if (Util.testOdds(Util.cookieDesire)){
            //Random customer_rand = new Random();
            //Randomly generate how many cookies customer wants
            int desired_cookies = Util.rndFromRange(1, Util.maxCookiesDesired);
            if (desired_cookies < store.getCookieInventory()){
                choice="bought " + desired_cookies + " cookies.";
                store.sellCookies(desired_cookies);
                cookiesConsumed = Util.consumed;
            }
            else{
                choice = "bought " + store.getCookieInventory() + " cookies but wanted " + desired_cookies + " cookies";
                store.sellCookies(store.getCookieInventory());
                cookiesConsumed = Util.dissapointed;
            }
        }
        return choice;
    }

    boolean isMonster(){
        return cookieMonster;
    }
}