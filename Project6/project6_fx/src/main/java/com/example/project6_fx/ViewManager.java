package com.example.project6_fx;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.io.File;
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



    public void drawWall(SimpleSquare square) {
        /**
         * TODO: Need a function from Square that gets all of the edges
         * Represent the appearance of an edge with 1 and no edge is 0
         *  - In edge, need a line object
         * */
        int[] edges = square.getEdges();
        int size = square.getEdges().length;
        Line lineAdd;
        for (int i = 0; i < size; i++) {
            /**
             *  Damage will be accounted at the update function
             * */
            if (edges[i] == 1) {
                if (i == 0) {
                    //Vertical right edge
                    lineAdd = new Line(square.rect.getWidth() + square.rect.getX(), square.rect.getY(), square.rect.getWidth() + square.rect.getX(), square.rect.getHeight());

                } else if (i == 1) {
                    //Vertical left edge
                    lineAdd = new Line(square.rect.getX(), square.rect.getY(), square.rect.getX(), square.rect.getHeight());
                } else if (i == 2) {
                    //Horizontal top edge
                    lineAdd = new Line(square.rect.getX(), square.rect.getY(), square.rect.getWidth() + square.rect.getX(), square.rect.getY());
                } else {
                    //Horizontal bottom edge
                    lineAdd = new Line(square.rect.getX(), square.rect.getY() + square.rect.getHeight(), square.rect.getWidth() + square.rect.getX(), square.rect.getY() + square.rect.getHeight());
                }
                lineAdd.setStroke(Color.BLACK);
                mainPane.getChildren().add(lineAdd);
            }
        }
    }

//    //TODO:Figure out the file path -> not working for some reason
    public void displayFire(SimpleSquare square){

        File fire = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/fire.png");
        Image fireImage = new Image(fire.toURI().toString());
        ImagePattern firePattern = new ImagePattern(fireImage);
        square.rect.setFill(firePattern);
        ImageView ffBlack = new ImageView(fireImage);
        ffBlack.setFitHeight(60);
        ffBlack.setFitWidth(60);

    }

    //TODO: Render FF
    public void displayFF(SimpleSquare square){
        File ffBlackPath = new File("/Users/angeldong/Desktop/CSCI5448/OOAD/Project6/project6_fx/src/main/graphics/black_ff.png");
        Image ffBlackImage = new Image(ffBlackPath.toURI().toString());
        ImagePattern ffPattern = new ImagePattern(ffBlackImage);
        square.rect.setFill(ffPattern);
        ImageView ffBlack = new ImageView(ffBlackImage);
        ffBlack.setFitHeight(60);
        ffBlack.setFitWidth(60);
    }
//    //TODO: Render POI


    //Drawing the map that is generated with Game (controller) this function will loop through all squares in the array list and display it
    //This is assuming that a Rectangle object is already an attribute of square - Take a look at SimpleMap
    //TODO: This will require change as there will be other objects placed on top of the squares
    public void drawMap(ArrayList<SimpleSquare>map){
        for(SimpleSquare singleSquare: map){
            mainPane.getChildren().add(singleSquare.rect);
            if(singleSquare.isFirefighter()){
                System.out.println("FF should appear");
                displayFF(singleSquare);
            }
            if(singleSquare.getFire()==2){
                displayFire(singleSquare);
            }
        }
    }

    //Updating the square by changing the property of the rectangle object that is associated with the square
    //TODO: The logic for updating a square, we could have several update square function for fire,smoke,etc. or just have this function handle the status updates
    public void updateSquare(SimpleSquare square){
        /**
         * These two piece of code is for updating a damaged wall -> if damage == 1
            Line damagedWall = square.getEdges()[0].line;
            damagedWall.getStrokeDashArray().addAll(10d, 5d);
         if damage == 0
         mainPane.getChildren.remove(wall)
         */
    }

    //TODO: Implement click event on the squares that will bring up the popup menu

}
