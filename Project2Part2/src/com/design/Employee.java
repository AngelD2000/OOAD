package com.design;

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