package com.design;

import java.util.*;


public abstract class Customer {
    private String name;
    private int cookiesConsumed = Util.noneConsumed;
    private Boolean cookieMonster = false;
    private int customerNum;
    private String type;
    private Demonstration[] demos;
    /**
     * Bonuses applied to a customer buying a game. .1 is 10% more likely
     * */
    HashMap<String, Double> purchaseBonus = new HashMap<String, Double>();

    Customer(int num, String name){
        customerNum = num;
        this.name = name;
        Demonstrate demonstrate = new Demonstrate(this);
        Explain explain = new Explain(this);
        Recommend recommend = new Recommend(this);
        demos = new Demonstration[] {demonstrate, explain, recommend, null};
    }

    public String getName() {
        return name;
    }

    public void setPurchaseBonus(String gameName, double bonus) {
        purchaseBonus.put(gameName, bonus);
    }
    public void incrementPurchaseBonus(String gameName, double bonus) {
        if(purchaseBonus.containsKey(gameName)){
            purchaseBonus.put(gameName, purchaseBonus.get(gameName)+bonus);
        }
        else{
            setPurchaseBonus(gameName, bonus);
        }
    }

    public double getPurchaseBonus(String gameName) {
        return purchaseBonus.get(gameName);
    }

    public void setCookieMonster(boolean val) {
        cookieMonster = val;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() { return type; }
    /**
     * Checks whether a customer is a monster or not
     * */
    boolean isMonster(){
        return cookieMonster;
    }


    /**
     * Customer decides how many cookies they want
     * Sets the cookiesConsumed if the customer ate, didn't eat, or wanted more cookies
     */
    String considerCookies(Store store){
        //Check if customer wants any cookies
        String choice = "no cookies";
        if (Util.testOdds(Util.cookieDesire)){
            //Randomly generate how many cookies customer wants
            int desired_cookies = Util.rndFromRange(1, Util.maxCookiesDesired);
            if (desired_cookies < store.getCookieInventory()){
                choice =  desired_cookies + " cookie(s).";
                store.sellCookies(desired_cookies);
                cookiesConsumed = Util.consumed;
            }
            else{
                choice = store.getCookieInventory() + " cookie(s), but customer wanted " + desired_cookies + " cookies";
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
    List<Integer> considerGames(GameList inventory){
        List<Integer> bought = new ArrayList<Integer>();
        //For every shelf position
        for (int j = 0; j < inventory.size(); j++) {
            //See if game picked in stock
            Game hold = inventory.getGameAtPos(j+1);
            if(hold.getCount() > 0) {
                //Base odds based on shelf position
                double buyOdds = .2 - (.02 * j);
                //Add in bonus for cookies
                buyOdds += Util.cookieOdds.get(cookiesConsumed);
                //Add in bonus from game type and demonstrator commands
                if(purchaseBonus.containsKey(hold.getGameName())){
                    buyOdds += purchaseBonus.get(hold.getGameName());
//                    Util.print(Double.toString(buyOdds));
                }
                if (bought.size() <= 2 && Util.testOdds(buyOdds)){
                    bought.add(j + 1);
                }
            }
        }
        return bought;
    }
    /**
     * Customer requests actions from demonstrator
     * */
    public void demandDemos(GameList inventory, Cashier cashier){
        Random rand = new Random();
        for (int i =0; i < Util.maxDemoActions; i++){
            int index = rand.nextInt(demos.length);
            Demonstration action = demos[index];
            if(Objects.isNull(action)){
                return;
            }
            else{
                cashier.demonstrate(inventory, action);
            }
        }
    }
}