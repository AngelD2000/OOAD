package com.design;

public abstract class Demonstration {
    Customer customer = null;
    Demonstration(){
        customer = null;
    }
    Demonstration(Customer customer){
        this.customer = customer;
    }
    public void execute(GameList inventory, Demonstrator demonstrator){
        String gameName = demonstrator.pickGame(inventory, customer);
        double increment = demonstrator.demonstrate(gameName, customer.getName());
        customer.incrementPurchaseBonus(gameName, increment);
    }
}

class Demonstrate extends Demonstration{
    Demonstrate(Customer customer){
        super(customer);
    }
    public void execute(GameList inventory, Demonstrator demonstrator){
        String gameName = demonstrator.pickGame(inventory, customer);
        double increment = demonstrator.demonstrate(gameName, customer.getName());
        customer.incrementPurchaseBonus(gameName, increment);
    }
}

class Explain extends Demonstration{
    Explain(Customer customer){
        super(customer);
    }
    public void execute(GameList inventory, Demonstrator demonstrator){
        String gameName = demonstrator.pickGame(inventory, customer);
        double increment = demonstrator.explain(gameName, customer.getName());
        customer.incrementPurchaseBonus(gameName, increment);
    }
}

class Recommend extends Demonstration{
    Recommend(Customer customer){
        super(customer);
    }
    public void execute(GameList inventory, Demonstrator demonstrator){
        String gameName = demonstrator.pickGame(inventory, customer);
        double increment = demonstrator.recommend(gameName, customer.getName());
        customer.incrementPurchaseBonus(gameName, increment);
    }
}