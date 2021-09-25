package com.design;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.LinkedList;

public class Announcer<String> extends Employee implements Flow.Subscriber<String>{
    private java.lang.String LOG_MESSAGE_FORMAT = "Subscriber %s >> [%s] %s%n";

    private static final int DEMAND = 3;
    private static final Random RANDOM = new Random();

    private String name;
    private Flow.Subscription subscription;

    private int count;

    public Announcer(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log("Subscribed");
        this.subscription = subscription;

        count = DEMAND;
        requestItems(DEMAND);
    }

    private void requestItems(int n) {
        log("Requesting new items...");
        subscription.request(n);
    }

    @Override
    public void onNext(String item) {
        System.out.println(item);
    }

    @Override
    public void onComplete() {
        log("Complete!");
    }

    @Override
    public void onError(Throwable t) {
        log("Subscriber Error >> %s", t);
    }

    private void log(java.lang.String message, Object... args) {
        System.out.printf(message);
    }
}
