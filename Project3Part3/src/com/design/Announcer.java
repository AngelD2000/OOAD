package com.design;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Flow;
import java.util.LinkedList;

public class Announcer extends Employee implements Flow.Subscriber<String> {
    private String name;
    private Flow.Subscription subscription;
    public List<String> consumedElements = new LinkedList<String>();

    private int count;

    public Announcer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Cashier announces who they are and that they have arrived at the store.
     */
    public void arrive(int day){
        System.out.println(name + " has arrived at the store on day " + day);
    }

    /**
     * Cashier announces who they are and that they are leaving the store.
     */
    public void leave(){
        System.out.println(name + " has left the store.");
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println(name + " Subscribed to " + subscription.getClass().getName());
        this.subscription = subscription;
        //Infinite buffer
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
        System.out.println(name + " Says: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }

    public static void printSimDayInfo(int day) {
        System.out.println("--- Simulation starting day " + String.valueOf(day));
    }
    public static void printEndDayInfo(int day) {
        System.out.println("End of day " + String.valueOf(day));
    }
}
