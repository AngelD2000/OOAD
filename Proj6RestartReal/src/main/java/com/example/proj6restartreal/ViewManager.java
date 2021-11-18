package com.example.proj6restartreal;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewManager {

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public ViewManager(){
        //Pane is the canvas we're putting stuff on
        mainPane = new AnchorPane();
        //Scene - how big the canvas is
        mainScene = new Scene(mainPane,Util.WIDTH,Util.HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }


    public Stage getMainStage(){
        return mainStage;
    }

    /**
     * @param:
     *      - square
     * Updates the square according to its properties
     * */

    public void updateSquare(Square square) {
        Image image = null;
        if (square.hasFire() || square.hasSmoke() || square.hasFF() || square.hasPoi() || square.hasVictim()) {
            if (square.hasFire()){
                image = new Image(Util.firePath);
            }
            if (square.hasSmoke()){
                image = new Image(Util.smokePath);
            }
            if (square.hasFF()){
                image = square.getFF().getImage();
                ImageView imageView = new ImageView(image);
                imageView.setOpacity(.5);

            }
            if (square.hasPoi()) {
                image = new Image(Util.poiPath);
            }

            if (square.hasVictim()){
                image = new Image(Util.personPath);
            }
            Rectangle rect = square.getRectangle();
            ImagePattern pattern = new ImagePattern(image);
            rect.setFill(pattern);

        }
    }


    /**
     * @param:
     *      - side, square, damage on that wall
     * Updates the edge according to damage taken
     * - 2: good edge
     * - 1: dashed edge
     * - 0: remove edge
     * */

    public void updateEdge(int side, Square square, int damage){
        Edge edge = square.getEdge(side);
        Line line = edge.getLine();
        if(damage == 1){
            line.getStrokeDashArray().addAll(25d, 15d);
            line.setStroke(Color.ORANGERED);
        }
        else{
            mainPane.getChildren().remove(line);
        }
    }

    /**
     * @param square
     * For every square, loops through the edges and draws the lines
     * */
    public void drawWall(Square square) {
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
                //update edge when damaged
                mainPane.getChildren().add(line);
            }
        }
    }

    /**
     * @param map
     * Uses the map iterator to loop through every single square and draws them
     * */
    public void drawMap(Map map){
        while (map.hasNext()) {
            Square square = map.next();
            Rectangle rectangle = square.getRectangle();
            if(square.isOutside()){
                rectangle.setFill(Color.GREEN);

            }
            else{
                rectangle.setFill(Color.WHITE);
            }
            rectangle.setY(square.getX()*Util.length);
            rectangle.setX(square.getY()*Util.length);
            rectangle.setWidth(Util.length);
            rectangle.setHeight(Util.length);
            mainPane.getChildren().add(rectangle);
            updateSquare(square);
            drawWall(square);
        }
        map.resetIterator();
    }

    /**
     * @param game
     * All texts on the canvas, gets the numbers from game since game knows about the numbers
     * */
    public void displayStatus(Game game) {
        Text currentFF = new Text("Current Firefighter: ");
        currentFF.setFont(Font.font("SansSerif"));
        currentFF.setX(Util.setDisplayX);
        currentFF.setY(50);

        Text peopleSaved = new Text("Victims Saved: " + game.building.saved);
        peopleSaved.setFont(Font.font("SansSerif"));
        peopleSaved.setX(Util.setDisplayX);
        peopleSaved.setY(370);

        Text peoplePerished = new Text("Victims Perished: " + game.building.perished);
        peoplePerished.setFont(Font.font("SansSerif"));
        peoplePerished.setX(Util.setDisplayX);
        peoplePerished.setY(390);

        Text damage = new Text("Building Integrity: " + game.getDamage());
        damage.setFont(Font.font("SansSerif"));
        damage.setX(Util.setDisplayX);
        damage.setY(410);

        mainPane.getChildren().addAll(currentFF,damage, peopleSaved, peoplePerished);
    }


    public void actionMenu() {

    }

}
