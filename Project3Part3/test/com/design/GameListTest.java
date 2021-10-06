package com.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameListTest {

    @Test
    void removeGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName, game);
        gamelist.get(gameName).setCount(1);
        int hold = gamelist.get(gameName).getCount();
        gamelist.removeGame(gameName,false);
        Assertions.assertFalse(gamelist.isEmpty());
        Assertions.assertEquals(gamelist.get(gameName).getCount(), hold-1);
    }

    @Test
    void addGame() {
        GameList gamelist = new GameList();
        String gameName = "Gloomhaven";
        Game game = new BoardGame(gameName, Util.assign_dim(), 20);
        gamelist.addGame(gameName,game);
        Assertions.assertEquals(1,gamelist.size());
    }
}