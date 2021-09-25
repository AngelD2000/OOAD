package com.design;

import java.util.concurrent.Flow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Employee {
    public String name;
    public String type;
    void setName(String newName){
        name = newName;
    }
    void setType(String newType){
        type = newType;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the name and type as a string of this employee
     */
    public String identifier(){
        return name + " the " + type;
    }
    /**
     * Has a cashier report that they are doing the action
     */
    public void report(String action){
        Util.print(identifier() + " " + action);
    }
    /**
     * Has a cashier report that they are doing the action + the day
     */
    public void report(String action, int day){
        Util.print(identifier() + " " + action + day + ".");
    }
}

//https://ozenero.com/java-9-flow-api-example-publisher-and-subscriber
class PublisherEmployee extends Employee implements Flow.Publisher {
    final ExecutorService executor = Executors.newFixedThreadPool(4);
    private List subscriptions = Collections.synchronizedList(new ArrayList());

    @Override
    public void subscribe(Subscriber subscriber) {
        MySubscription subscription = new MySubscription(subscriber);
        subscriptions.add(subscription);
        subscriber.onSubscribe(subscription);
    }
}

class MySubscription implements Subscription {

    private Subscriber subscriber;
    private final AtomicInteger value;
    private AtomicBoolean isCanceled;

    public MySubscription(Subscriber subscriber) {
        this.subscriber = subscriber;

        value = new AtomicInteger();
        isCanceled = new AtomicBoolean(false);
    }

    @Override
    public void request(long n) {
        if (isCanceled.get())
            return;
        else
            publishItems(n);
    }

    private void publishItems(long n) {
        for (int i = 0; i < n; i++) {
            String v = "Test";
            subscriber.onNext(v);
        }
    }

    @Override
    public void cancel() {
        isCanceled.set(true);
    }
}