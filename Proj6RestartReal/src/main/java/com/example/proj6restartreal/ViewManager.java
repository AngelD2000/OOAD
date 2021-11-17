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

    public void displayElement(Square square) {
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
        for (Edge edge: square.getEdges()) {
            //Add each edge to the mainPane ex. mainPane.getChildren().add(edge.line)
            //square.edge.line.setStroke(2);
            //If it is damaged: lineAdd.getStrokeDashArray().addAll(25d, 10d);
            Line line = edge.getLine();
            if(edge.getDamage() == 2){
                line.setFill(Color.BLACK);
                line.setStrokeWidth(3);
                mainPane.getChildren().add(line);
            }
            else if(edge.getDamage() == 1){
                line.setFill(Color.ORANGERED);
                line.getStrokeDashArray().addAll(25d, 15d);
                mainPane.getChildren().add(line);
            }
            else{
                mainPane.getChildren().remove(line);
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
            if(square instanceof OutsideSquare){
                rectangle.setFill(Color.GREEN);
            }
            else{
                rectangle.setFill(Color.WHITE);
            }
            rectangle.setX(square.getX());
            rectangle.setY(square.getY());
            mainPane.getChildren().add(rectangle);
            break;
        }
        map.resetIterator();
    }


    /**
     * Argument needed:
     * - Current square
     * */
    public void updateSquare(Square square) {
        displayElement(square);
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
