package com.example.project6_fx;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ViewManager {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 700;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,WIDTH,HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    //Drawing the map that is generated, this function will loop through all squares in the array list and display it
    //This is assuming that a Rectangle object is already an attribute of square
    public void drawMap(ArrayList<SimpleSquare>map){
        for(SimpleSquare singleSquare: map){
            mainPane.getChildren().add(singleSquare.rect);
        }
    }

    //Possibly need several update square or more arguments for fire, smoke etc.
    public void updateSquare(SimpleSquare square){
        square.rect.setFill(Color.RED);
    }

}
