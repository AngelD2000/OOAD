package com.example.project6_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

//https://stackoverflow.com/questions/35294207/place-multiple-rectangles-in-javafx-randomly

public class Flashpoint extends Application implements GameViewInterface {
    private  ViewManager manager = new ViewManager();
    private SimpleGame game = new SimpleGame();

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage = manager.getMainStage();
        manager.drawMap(game.map);

        //Trying to simulate a change in game, this will turn one of the squares red
        //game.event();
        primaryStage.show();
    }

    //Draw an updated square
    @Override
    public void drawUpdateSquare(SimpleSquare square){
        manager.updateSquare(square);
    }

    @Override
    public void ActionMenu() {

    }
    public static void main(String args[]){
        launch(Flashpoint.class);
    }
}
