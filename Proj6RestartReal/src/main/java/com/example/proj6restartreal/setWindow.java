package com.example.proj6restartreal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class setWindow {
    private final ViewManager manager;
    private final MapView mapView;
    private ArrayList<Text> status;
    private ArrayList<Rectangle> ffRect;

    setWindow(ViewManager manager){
        this.manager = manager;
        mapView = new MapView(this);
        setStatus();
    }

    public ArrayList<Text> getStatus() {
        return status;
    }

    public ArrayList<Rectangle> getFfRect() {
        return ffRect;
    }

    /**
     * Creates the green border underneath for the game board
     */
    public void createBorder(){
        for(int i = 0; i < Util.mapWidth; i++){
            for(int j = 0; j < Util.mapHeight; j++){
                if(i == 0 || i == Util.mapWidth-1 || j == 0 || j ==Util.mapHeight-1){
                    Rectangle rect = new Rectangle(i * Util.length, j * Util.length, Util.length,Util.length);
                    rect.setFill(Color.GREEN);
                    manager.getMainPane().getChildren().add(rect);
                }
            }
        }
    }

    public void drawMap(boolean flag){
        mapView.mapDisplay(flag);
    }

    /**
     *  Set the initial status bar on the right side of the board
     */
    public void setStatus() {
        this.ffRect = new ArrayList<>();
        this.status = new ArrayList<>();

        Text currentFFStr = new Text("Current Firefighter: ");
        currentFFStr.setFont(Font.font("SansSerif"));
        currentFFStr.setX(Util.setDisplayX);
        currentFFStr.setY(50);

        mapView.displayFFTurn(ffRect);

        Text numActions =  new Text(manager.getGame().firefighterLogic.getActions() + " Actions");
        numActions.setFont(Font.font("SansSerif"));
        numActions.setX(Util.setDisplayX + 50);
        numActions.setY(100);

        Text nextFFStr = new Text("Next Firefighters: ");
        nextFFStr.setFont(Font.font("SansSerif"));
        nextFFStr.setX(Util.setDisplayX);
        nextFFStr.setY(130);

        Text peopleSaved = new Text("Victims Saved: " + manager.getGame().building.saved);
        peopleSaved.setFont(Font.font("SansSerif"));
        peopleSaved.setX(Util.setDisplayX);
        peopleSaved.setY(370);

        Text peoplePerished = new Text("Victims Perished: " + manager.getGame().building.perished);
        peoplePerished.setFont(Font.font("SansSerif"));
        peoplePerished.setX(Util.setDisplayX);
        peoplePerished.setY(390);

        Text damage = new Text("Building Integrity: " + manager.getGame().getDamage());
        damage.setFont(Font.font("SansSerif"));
        damage.setX(Util.setDisplayX);
        damage.setY(410);

        status.add(currentFFStr);
        status.add(damage);
        status.add(peopleSaved);
        status.add(peoplePerished);
        status.add(nextFFStr);
        status.add(numActions);
        manager.getMainPane().getChildren().addAll(currentFFStr,damage, peopleSaved, peoplePerished,nextFFStr,numActions);
    }

    public ViewManager getManager() {
        return manager;
    }
}
