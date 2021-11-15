package com.example.project6_fx;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewManager implements GameViewInterface {


    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private Display display = new Display();
    private Game game = new Game();

    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane,Util.WIDTH,Util.HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }

    public Stage getMainStage(){
        return mainStage;
    }

    //TODO: Argument is a single square
    public void drawWall() {
        //TODO: Iterate through the edges of each square
        for () {
            //Add each edge to the mainPane ex. mainPane.getChildren().add(edge.line)
            mainPane.getChildren().add(/*edge.line*/);
        }
    }

    //Drawing the map that is generated with Game (controller) this function will loop through all squares in the array list and display it
    //This is assuming that a Rectangle object is already an attribute of square - Take a look at SimpleMap
    //TODO: This will require change as there will be other objects placed on top of the squares
    public void drawMap(){
        for(){
            //TODO: Need a Map iterator here, each square should be linked with a Rectangle object from Javafx
            //Example: mainPane.getChildren().add(square.rect) or something of equivalent
            mainPane.getChildren().add(/*square.rectangle*/);
            drawWall();
            display.displayElement(/*square*/);
        }
    }


    /**
     * Argument needed:
     * - Current square
     * */
    @Override
    public void updateSquare(Square square) {
        display.displayElement(square);
    }

    @Override
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


    @Override
    public void actionMenu() {

    }

}
