package com.design;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.LinkedList;

// Announcer implements Flow.Subscriber to become an observer
public class Announcer extends Employee implements Flow.Subscriber<String> {
    private Flow.Subscription subscription;
    public List<String> consumedElements = new LinkedList<String>();

    public Announcer(String name) {
        setType("Announcer");
        setName(name);
    }

    /**
     * Announcer announces who they are and that they have arrived at the store.
     */
    public void arrive(int day){
        System.out.println(getName() + " has arrived at the store on day " + day);
    }

    /**
     * Announcer announces who they are and that they are leaving the store.
     */
    public void leave(){
        System.out.println(getName() + " has left the store.");
    }

    /**
     * implements method from Flow.Subscriber to handle addition of a subscription between a publisher and this.
     * @param subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
//        System.out.println(name + " Subscribed to " + subscription.);
        this.subscription = subscription;
        //Infinite buffer
        subscription.request(Long.MAX_VALUE);
    }

    /**
     * Implements method from Flow.Subscriber to handle each message as it is received. Prints message and ads it to
     * a list of all messages processed.
     * @param item
     */
    @Override
    public void onNext(String item) {
        System.out.println(getName() + " Says: " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    /**
     * Implements method from Flow.Subscriber to handle errors.
     * @param t
     */
    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    /**
     * Implements method from Flow.Subscriber to handle exiting.
     */
    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}
