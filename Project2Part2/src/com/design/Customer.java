package com.design;

import java.util.Random;

public class Customer {
    int cookiesConsumed = Util.noneConsumed;
    Boolean cookieMonster = false;
    void considerCookies(int cookies){
        double randomValue = Math.random();
        //Check if customer wants any cookies
        if (randomValue < Util.cookieDesire){
            Random customer_rand = new Random();
            //Randomly generate how many cookies customer wants
            int desired_cookies = customer_rand.nextInt(Util.maxCookiesDesired-1) + 1;
            if (desired_cookies < cookies){
                cookies -= desired_cookies;
                cookiesConsumed = Util.consumed;
            }
            else{
                cookies = 0;
                cookiesConsumed = Util.dissapointed;
            }
        }
    }
}
