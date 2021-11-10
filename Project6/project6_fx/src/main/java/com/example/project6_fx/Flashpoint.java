package com.example.project6_fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;

public class Flashpoint extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //https://stackoverflow.com/questions/35294207/place-multiple-rectangles-in-javafx-randomly
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        int columns = 7, rows = 10, horizontal = 60, vertical = 60;
        Rectangle rect = null;
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                rect = new Rectangle(horizontal * j, vertical * i, horizontal, vertical);
                if(i == 0 || j == 0 ||i == columns - 1 || j == rows - 1){
                    rect.setFill(Color.GREEN);
                }
                else{
                    rect.setFill(Color.WHITE);
                }
                rect.setStroke(Color.BLACK);
                root.getChildren().add(rect);
            }
        }
        scene.setRoot(root);
        stage.show();
    }
/*
    public void displaySquare(SimpleSquare square, String color){
        int[] coord = square.getCoordinate();
        Rectangle rect = new Rectangle(coord[1],coord[2], Util.horizontal,Util.vertical);
        if(color == "green"){
            rect.setFill(Color.GREEN);
        }
        else{
            rect.setFill(Color.WHITE);
        }
        rect.setStroke(Color.BLACK);
    }

 */

    public static void main(String args[]){
        launch(args);
        int[] loc = {0,1};
        SimpleSquare testSquare = new SimpleSquare(0,0,1,false, loc);
        GameView gameV = new GameView();
        gameV.renderSquare(testSquare);
    }
}
