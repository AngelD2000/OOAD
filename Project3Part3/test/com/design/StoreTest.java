package com.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void breakGame() {
        Store store = new Store();
        String gameName = "BoardGame";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        game.setCount(Util.maxInventory);
        store.inventory.put(gameName,game);
        store.breakGame();
        Assertions.assertEquals(1,store.brokenGames.size());
    }

    @Test
    void rampage() {
        Store store = new Store();
        Cashier cashier = new Cashier("CookieBen", .2, Util.odd);
        store.cookies = 10;
        store.currentCashier = cashier;
        store.rampage();
        Assertions.assertEquals(10, store.eatenCookies);
    }
}