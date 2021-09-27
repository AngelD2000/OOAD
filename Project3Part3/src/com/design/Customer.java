package com.design;

import java.util.Random;

public class Customer {
    int cookiesConsumed = Util.noneConsumed;
    Boolean cookieMonster = false;
    int customerNum;
    Customer(double monsterChance, int num){
        double randomValue = Math.random();
        cookieMonster = Util.testOdds(Util.monsterChance);
        customerNum = num;
    }
    /**
     * Customer decides how many cookies they want
     * Sets the cookiesConsumed if the customer ate, didn't eat, or wanted more cookies
     */
    void considerCookies(Store store){
        double randomValue = Math.random();
        //Check if customer wants any cookies
        if (Util.testOdds(Util.cookieDesire)){
            Random customer_rand = new Random();
            //Randomly generate how many cookies customer wants
            int desired_cookies = Util.rndFromRange(1, Util.maxCookiesDesired);
            if (desired_cookies < store.cookies){
                customerReport("bought " + desired_cookies + " cookies.");
                store.sellCookies(desired_cookies);
            }
            else{
                customerReport("bought " + store.cookies + " cookies but wanted " + desired_cookies + " cookies");
                store.sellCookies(store.cookies);
            }
        }
        else{
            customerReport("didn't want any cookies");
        }
    }

    boolean isMonster(){
        return cookieMonster;
    }

    void customerReport(String action){
        Util.print("Customer " + customerNum + " " + action);
    }
}