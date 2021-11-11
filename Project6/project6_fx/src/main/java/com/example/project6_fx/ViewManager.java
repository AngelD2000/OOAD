package com.example.project6_fx;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ViewManager {
    //Feel free to change the screen size
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

    //Drawing the map that is generated with Game (controller) this function will loop through all squares in the array list and display it
    //This is assuming that a Rectangle object is already an attribute of square - Take a look at SimpleMap
    //TODO: This will require change as there will be other objects placed on top of the squares
    public void drawMap(ArrayList<SimpleSquare>map){
        for(SimpleSquare singleSquare: map){
            mainPane.getChildren().add(singleSquare.rect);
        }
    }

    //Updating the square by changing the property of the rectangle object that is associated with the square
    //TODO: The logic for updating a square, we could have several update square function for fire,smoke,etc. or just have this function handle the status updates
    public void updateSquare(SimpleSquare square){
        square.rect.setFill(Color.RED);
    }

    //TODO: Implement click event on the squares that will bring up the popup menu

}
