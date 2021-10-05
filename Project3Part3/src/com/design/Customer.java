package com.design;

import java.util.ArrayList;
import java.util.List;


public class Customer {
    int cookiesConsumed = Util.noneConsumed;
    Boolean cookieMonster = false;
    int customerNum;
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
        String choice = " didn't want any cookies";
        if (Util.testOdds(Util.cookieDesire)){
            //Randomly generate how many cookies customer wants
            int desired_cookies = Util.rndFromRange(1, Util.maxCookiesDesired);
            if (desired_cookies < store.getCookieInventory()){
                choice=" bought " + desired_cookies + " cookies.";
                store.sellCookies(desired_cookies);
                cookiesConsumed = Util.consumed;
            }
            else{
                choice = " bought " + store.getCookieInventory() + " cookies but wanted " + desired_cookies + " cookies";
                store.sellCookies(store.getCookieInventory());
                cookiesConsumed = Util.dissapointed;
            }
        }
        return choice;
    }
    /**
     * Customer decides what games/ how many games they want to buy
     * Puts them in the bought arraylist (the shopping cart)
     * Returns the list
     * */
    List<Integer> considerGames(GameList inventory, double additional_odds){
        List<Integer> bought = new ArrayList<Integer>();
        //For every shelf position
        for (int j = 0; j < inventory.size(); j++) {
            //See if game picked in stock
            if(inventory.getGameAtPos(j+1).getCount() > 0) {
                if (Util.testOdds(.2 - (.02 * j) + additional_odds) && bought.size() < 2) {
                    bought.add(j + 1);
                }
            }
        }
        return bought;
    }

    /**
     * Checks whether a customer is a monster or not
     * */
    boolean isMonster(){
        return cookieMonster;
    }
}