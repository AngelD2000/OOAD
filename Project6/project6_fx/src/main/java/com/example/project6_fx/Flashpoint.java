package com.example.project6_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

//https://stackoverflow.com/questions/35294207/place-multiple-rectangles-in-javafx-randomly

public class Flashpoint extends Application implements GameViewInterface {
    private  ViewManager manager = new ViewManager();
    private SimpleGame game = new SimpleGame();

    /**
     * This function is at the top most level of the program with Applications
     *    - JavaFX is a single threaded program, with one scene per stage
     *  In order to use the scene and stage, both of them are declared ViewManager along with the size of the window
     * */

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            primaryStage = manager.getMainStage();
            manager.drawMap(game.map);

            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** Draw the updated square by changing the property of the current square */
    @Override
    public void drawUpdateSquare(SimpleSquare square){
        manager.updateSquare(square);
    }

    //TODO: This function should bring up the menu when the event of mouse click on a square happens
    @Override
    public void actionMenu() {

    }
    public static void main(String args[]){
        launch(Flashpoint.class);
    }
}
