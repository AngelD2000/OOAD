package com.design;

public class Main {
    public static void main(String[] args) {
        int days = 11;
        Store store = new Store();
        for(int i = 0; i < days; i++){
            store.runDay(i + 1);
        }
        store.finalSummary();
    }
}