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
    public double getStoreTotal() {
        return storeTotal;
    }

    public void printAmount() {Util.print("Register has $" + getStoreTotal());}

    public void checkIfNeedFill(){
        if (storeTotal < 100){
            setStoreTotal(1000 + storeTotal);
            setMoneyFills(moneyFills + 1);
            Util.print("Register had to be filled");
            printAmount();
        }
    }
}
