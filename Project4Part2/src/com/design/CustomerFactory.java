package com.design;

public class CustomerFactory {
    private static final CustomerFactory instance = new CustomerFactory();

    private CustomerFactory() {}

    public static CustomerFactory getInstance() {
        return instance;
    }

    public Customer getCustomer(int num) {
        switch(Util.getCustomerType()) {
            case "Family Gamer":
                return new FamilyGamer(num, Util.pickName());
            case "Kid Gamer":
                return new KidGamer(num, Util.pickName());
            case "Card Gamer":
                return new CardGamer(num, Util.pickName());
            case "Board Gamer":
                return new BoardGamer(num, Util.pickName());
            case "Cookie Monster":
                return new CookieMonster(num, Util.pickName());
            default:
                return null;
        }
    }
}
