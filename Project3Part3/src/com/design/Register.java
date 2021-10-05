package com.design;

public class Register {

    private int moneyFills = 0;
    private double storeTotal = 0;

    public int getMoneyFills() {
        return moneyFills;
    }

    public void setMoneyFills(int moneyFills) {
        this.moneyFills = moneyFills;
    }

    public void setStoreTotal(double storeTotal) {
        this.storeTotal = storeTotal;
    }
    public void incrementStoreTotal(double value) {
        setStoreTotal(this.storeTotal += value);
    }
    public double getStoreTotal() {
        return storeTotal;
    }

    public String printAmount() { return "register has " + Util.asDollar(getStoreTotal());}

    /**
     * Check is the register needs to be refilled, if so, fills it.
     */
    public String checkIfNeedFill(){
        String answer = printAmount();
        if (storeTotal < 100){
            setStoreTotal(1000 + storeTotal);
            setMoneyFills(moneyFills + 1);
            answer += ", so it was refilled";
            printAmount();
        }
        return answer;
    }
}
