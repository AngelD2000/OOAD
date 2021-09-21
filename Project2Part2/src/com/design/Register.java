package com.design;

public class Register {
    private int moneyFills = 0;
    private float storeTotal = 0;

    public int getMoneyFills() {
        return moneyFills;
    }

    public void setMoneyFills(int moneyFills) {
        this.moneyFills = moneyFills;
    }

    public void setStoreTotal(float storeTotal) {
        this.storeTotal = storeTotal;
    }
    public float getStoreTotal() {
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
