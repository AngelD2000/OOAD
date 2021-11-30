package com.example.proj6restartreal;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MapView {
    private final setWindow window;
    MapView(setWindow window){
        this.window = window;
    }

    /**
     * @param map
     * For every square, loops through the edges and draws the lines
     * */
    public void drawWall(boolean flag,Map map) {
        ViewManager manager = window.getManager();
        while(map.hasNext()){
            Square square = map.next();
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
        map.resetIterator();
    }

    /**
     * Uses the map iterator to loop through every single square and draws them
     * */
    public void mapDisplay(boolean flag){
        ViewManager manager = window.getManager();
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
        }
        map.resetIterator();
        drawWall(flag,map);
    }
    /**
     * Creates new rectangles and render the firefighter images for status
     */
    public void displayFFTurn(ArrayList<Rectangle> ffRect){
        ViewManager manager = window.getManager();
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
}
