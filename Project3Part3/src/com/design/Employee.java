package com.design;

import java.util.concurrent.SubmissionPublisher;

abstract class Employee extends SubmissionPublisher<String> {
    public String name;
    public String type;

    public void setName(String newName) {
        name = newName;
    }

    public void setType(String newType) {
        type = newType;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the name and type as a string of this employee
     */
    public String identifier() {
        return name + " the " + type;
    }

    /**
     * Has a cashier report that they are doing the action
     */
    public void report(String action) {
        submit(identifier() + " " + action);
//        Util.print(identifier() + " " + action);
    }

    /**
     * Has a cashier report that they are doing the action + the day
     */
    public void report(String action, int day) {
        submit(identifier() + " " + action + day + ".");
//        Util.print(identifier() + " " + action + day + ".");
    }
}