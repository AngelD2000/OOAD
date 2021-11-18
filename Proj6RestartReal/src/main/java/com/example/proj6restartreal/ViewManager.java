package com.example.proj6restartreal;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewManager {


    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,Util.WIDTH,Util.HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    public void squareUpdate(Square square) {
        Image image = null;
        if (square.hasFire() || square.hasSmoke() || square.hasFF() || square.hasPoi() || square.hasVictim()) {
            if (square.hasFire())
                image = new Image(Util.firePath);
            if (square.hasSmoke())
                image = new Image(Util.smokePath);
            if (square.hasFF())
                image = square.getFF().getImage();
            if (square.hasPoi())
                image = new Image(Util.poiPath);
            if (square.hasVictim())
                image = new Image(Util.personPath);

            Rectangle rect = square.getRectangle();
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);
            ImageView view = new ImageView(image);
            view.setFitHeight(60);
            view.setFitWidth(60);
        }
    }

    //TODO: Argument is a single square
    public void drawWall(Square square) {
        //TODO: Iterate through the edges of each square
        for(int i = 0; i < 4; i++){
            Edge edge = square.getEdge(i);
            if(edge != null){
                Line line = edge.getLine();
                double x = square.getRectangle().getX();
                double y = square.getRectangle().getY();
                if(i == 0){
                    line.setStartX(x);
                    line.setStartY(y);
                    line.setEndX(Util.length + x);
                    line.setEndY(y);
                }
                else if(i == 1){
                    line.setStartX(x);
                    line.setStartY(y + Util.length);
                    line.setEndX(Util.length + x);
                    line.setEndY(y + Util.length);

                }
                else if(i == 2){
                    line.setStartX(Util.length + x);
                    line.setStartY(y);
                    line.setEndX(Util.length + x);
                    line.setEndY(Util.length);
                }

                else{
                    line.setStartX(x);
                    line.setStartY(y);
                    line.setEndX(x);
                    line.setEndY(Util.length);
                }
                mainPane.getChildren().add(line);
            }
        }
    }

    //Drawing the map that is generated with Game (controller) this function will loop through all squares in the array list and display it
    //This is assuming that a Rectangle object is already an attribute of square - Take a look at SimpleMap
    //TODO: This will require change as there will be other objects placed on top of the squares
    public void drawMap(Map map){
        while (map.hasNext()) {
            Square square = map.next();
            Rectangle rectangle = square.getRectangle();
            if(square.isOutside()){
                rectangle.setFill(Color.GREEN);
            }
            else if (square.hasFire()) {
                rectangle.setFill(Color.RED);
            }
            else{
                rectangle.setFill(Color.WHITE);
            }
            rectangle.setY(square.getX()*Util.length);
            rectangle.setX(square.getY()*Util.length);
            rectangle.setWidth(Util.length);
            rectangle.setHeight(Util.length);
            mainPane.getChildren().add(rectangle);
            //drawWall(square);
            //displayElement(square);

        }
        map.resetIterator();
    }


    /**
     * Argument needed:
     * - Current square
     * */
    public void updateSquare(Square square) {
        squareUpdate(square);
    }

    /**
     * TODO: Display how many people saved and damage of the building
     * */
    public void displayStatus(Game game) {
        Text damage = new Text("Total Damage: " + game.getDamage());
        damage.setX(Util.setDisplayX);
        damage.setY(30);

        Text peopleSaved = new Text("Total People Saved: " + game.building.saved);
        peopleSaved.setX(Util.setDisplayX);
        peopleSaved.setY(50);

        Text peoplePerished = new Text("Total People Saved: " + game.building.perished);
        peoplePerished.setX(Util.setDisplayX);
        peoplePerished.setY(50);

        mainPane.getChildren().addAll(damage, peopleSaved, peoplePerished);
    }


    public void actionMenu() {

    }

}
