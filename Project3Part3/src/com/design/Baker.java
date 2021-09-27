package com.design;

public class Baker extends PublisherEmployee{
    double pocketTotal;
    int dozenPerDay = 1;
    Baker(String setName){
        setType("Baker");
        setName(setName);
    }
    /**
     * Baker will sell cookies.
     * Will increase pocket total by pricePerDozen*dozenPerDay and return that amount
     */
    double sellCookies(){
        report("has arrived at the store.");
        report("has sold " + dozenPerDay + " packages of cookies");
        double cost =  Util.cookiePricePerDozen*dozenPerDay;
        report("has charged the store " + Util.asDollar(cost));
        pocketTotal += cost;
        report("has left store");
        return cost;
    }
    int getDozenPerDay(){
        return dozenPerDay;
    }
    double getPocketAmount(){
        return pocketTotal;
    }
    void incrementDozenPerDay(int value){
        dozenPerDay += value;
        if (dozenPerDay < 1){
            dozenPerDay = 1;
        }
    }
}
