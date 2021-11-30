package com.example.proj6restartreal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class setWindow {
    private ViewManager manager;
    private ArrayList<Text> status;
    private ArrayList<Rectangle> ffRect;

    setWindow(ViewManager manager){
        this.manager = manager;
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


    /**
     * @param square
     * For every square, loops through the edges and draws the lines
     * */
    public void drawWall(Square square,boolean flag) {
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
                    line.setEndY(y+ Util.length);
                }

                else {
                    line.setStartX(x);
                    line.setStartY(y);
                    line.setEndX(x);
                    line.setEndY(y + Util.length);
                }
                if(flag && !manager.getMainPane().getChildren().contains(line)){
                    manager.getMainPane().getChildren().add(line);
                }
                manager.updateEdge(i,square);
            }
        }
    }

    /**
     * Uses the map iterator to loop through every single square and draws them
     * */
    public void drawMap(boolean flag){
        Map map = manager.getGame().getMap();
        while (map.hasNext()) {
            Square square = map.next();
            Rectangle rectangle = square.getRectangle();
            if(square.isOutside()){
                rectangle.setFill(Color.GREEN);
            }
            else{
                rectangle.setFill(Color.WHITE);
            }
            if(flag){
                rectangle.setY(square.getX()*Util.length);
                rectangle.setX(square.getY()*Util.length);
                rectangle.setWidth(Util.length);
                rectangle.setHeight(Util.length);
                manager.getMainPane().getChildren().add(rectangle);
            }
            manager.updateSquare(square);
            drawWall(square,flag);
        }
        map.resetIterator();
    }


    /**
     * Creates new rectangles and render the firefighter images for status
     */
    public void displayFFTurn(){
        int len = 40;
        int currentFireFighter = manager.getGame().firefighterLogic.company.activeFirefighter;
        String currentFFPath = Util.firefighterImages[currentFireFighter];
        int height = 90;
        int displayed = 0;
        int i = 0;
        boolean startRender = false;
        while(displayed < Util.numFirefighters){
            if(i == currentFireFighter){
                Rectangle rect = new Rectangle(Util.setDisplayX, 70,len,len);
                Image ffImage = new Image(currentFFPath);
                ImagePattern ffPattern = new ImagePattern(ffImage);
                rect.setFill(ffPattern);
                manager.getMainPane().getChildren().add(rect);
                ffRect.add(rect);
                startRender = true;
                displayed++;
            }
            i++;
            if(i == Util.numFirefighters){
                i = 0;
            }
            if(startRender){
                height += (len + 10);
                Rectangle rect = new Rectangle(Util.setDisplayX, height,len,len);
                Image ffImage = new Image(Util.firefighterImages[i]);
                ImagePattern ffPattern = new ImagePattern(ffImage);
                rect.setFill(ffPattern);
                manager.getMainPane().getChildren().add(rect);
                ffRect.add(rect);
                displayed++;
            }

        }
    }

    /**
     * Display status
     */
    public void setStatus() {
        this.ffRect = new ArrayList<>();
        this.status = new ArrayList<>();

        Text currentFFStr = new Text("Current Firefighter: ");
        currentFFStr.setFont(Font.font("SansSerif"));
        currentFFStr.setX(Util.setDisplayX);
        currentFFStr.setY(50);

        displayFFTurn();

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
}
