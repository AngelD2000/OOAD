package com.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    @Test
    void orderGame() {
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        game.setCount(Util.maxInventory);
        Cashier cashier = new Cashier("Bernie", .3, Util.height);
        GameList inventory = new GameList();
        inventory.addGame(gameName,game);
        GameList orderGames = new GameList();
        orderGames.addGame(gameName,game);
        Register register = new Register();
        register.setStoreTotal(300);

        //Customer bought all games
        inventory.get(gameName).setCount(0);
        cashier.orderGame(orderGames,inventory,register);

        Assertions.assertEquals(270, register.getStoreTotal());

        cashier.unpackOrders(inventory, orderGames);
        Assertions.assertEquals(3, inventory.get(gameName).getCount());
        Assertions.assertEquals(20, inventory.get(gameName).getPrice());
    }

    @Test
    void orderCookies() {
        Cashier cashier = new Cashier("Bern", .3, Util.wide);
        Baker baker = new Baker("Bob");
        Store store = new Store();
        store.cookies = 0;
        cashier.orderCookies(baker,store);
        Assertions.assertEquals(2, baker.getDozenPerDay());
    }
}