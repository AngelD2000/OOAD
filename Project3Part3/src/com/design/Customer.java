package com.design;

import java.util.Random;

public class Customer {
    int cookiesConsumed = Util.noneConsumed;
    Boolean cookieMonster = false;
    int customerNum;
    Customer(double monsterChance, int num){
        double randomValue = Math.random();
        if(randomValue < monsterChance){
            cookieMonster = true;
        }
        customerNum = num;
    }
    void considerCookies(Store store){
        double randomValue = Math.random();
        //Check if customer wants any cookies
        if (randomValue < Util.cookieDesire){
            Random customer_rand = new Random();
            //Randomly generate how many cookies customer wants
            int desired_cookies = customer_rand.nextInt(Util.maxCookiesDesired-1) + 1;
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