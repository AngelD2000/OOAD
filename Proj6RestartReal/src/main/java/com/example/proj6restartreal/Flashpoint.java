package com.example.proj6restartreal;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

//https://stackoverflow.com/questions/35294207/place-multiple-rectangles-in-javafx-randomly

public class Flashpoint extends Application {
    private ViewManager manager = new ViewManager();

    /**
     * This function is at the top most level of the program with Applications
     *    - JavaFX is a single threaded program, with one scene per stage
     *  In order to use the scene and stage, both of them are declared ViewManager along with the size of the window
     * */

    @Override
    public void start(Stage primaryStage) throws IOException {
        try{

            primaryStage = manager.getMainStage();
            manager.getWindow().drawMap(true);
            manager.getMenu().createMenu();
            manager.setMenu();
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        launch(Flashpoint.class);
    }
}
